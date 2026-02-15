package com.hackathon_iste.demo.Service;

import com.hackathon_iste.demo.Repository.CityEventVisibilityRepository;
import com.hackathon_iste.demo.Repository.EventReplayRepository;
import com.hackathon_iste.demo.Repository.SpaceEventRepository;
import com.hackathon_iste.demo.dto.*;
import com.hackathon_iste.demo.model.EventReplay;
import com.hackathon_iste.demo.model.SpaceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpaceEventService {

    private final SpaceEventRepository spaceEventRepository;
    private final EventReplayRepository eventReplayRepository;
    private final CityEventVisibilityRepository cityEventVisibilityRepository;

    /**
     * Get recent past launches (that user might have missed)
     */
    public List<LaunchResponse> getMissedLaunches(int daysAgo) {
        try {
            LocalDateTime cutoffDate = LocalDateTime.now().minusDays(daysAgo);
            LocalDateTime now = LocalDateTime.now();

            List<SpaceEvent> pastEvents = spaceEventRepository.findEventsByDateRange(cutoffDate, now);

            List<LaunchResponse> missedLaunches = pastEvents.stream()
                    .map(this::mapToLaunchResponse)
                    .collect(Collectors.toList());

            log.info("Found {} missed launches in the last {} days", missedLaunches.size(), daysAgo);
            return missedLaunches;

        } catch (Exception e) {
            log.error("Error fetching missed launches: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Get detailed replay info for a specific launch
     */
    public LaunchReplayResponse getLaunchReplay(Long eventId) {
        try {
            SpaceEvent event = spaceEventRepository.findById(eventId)
                    .orElse(null);

            if (event == null) {
                log.warn("Event not found with ID: {}", eventId);
                return null;
            }

            LaunchResponse launch = mapToLaunchResponse(event);

            // Get replay videos and highlights
            List<EventReplay> replays = eventReplayRepository.findBySpaceEvent_EventId(eventId);

            List<String> replayVideos = replays.stream()
                    .map(EventReplay::getVideoUrl)
                    .filter(url -> url != null && !url.isEmpty())
                    .collect(Collectors.toList());

            List<String> highlights = replays.stream()
                    .map(EventReplay::getHighlightText)
                    .filter(text -> text != null && !text.isEmpty())
                    .collect(Collectors.toList());

            String photoGalleryUrl = replays.isEmpty() ? null : replays.get(0).getPhotoGalleryUrl();
            Integer photosCount = replays.isEmpty() ? 0 : replays.get(0).getPhotosCount();

            return LaunchReplayResponse.builder()
                    .launch(launch)
                    .replayVideos(replayVideos)
                    .highlights(highlights)
                    .summary(event.getDescription())
                    .wasSuccessful(event.getWasSuccessful())
                    .photoGalleryUrl(photoGalleryUrl)
                    .photosCount(photosCount)
                    .viewCount(event.getViewCount())
                    .likesCount(event.getLikesCount())
                    .build();

        } catch (Exception e) {
            log.error("Error fetching launch replay for event {}: {}", eventId, e.getMessage());
            return null;
        }
    }

    /**
     * Get all events with replay available
     */
    public List<LaunchResponse> getAllEventsWithReplay() {
        try {
            List<SpaceEvent> events = spaceEventRepository.findByReplayAvailableTrue();

            return events.stream()
                    .map(this::mapToLaunchResponse)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error fetching events with replay: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Get upcoming launches
     */
    public List<LaunchResponse> getUpcomingLaunches() {
        try {
            LocalDateTime now = LocalDateTime.now();
            List<SpaceEvent> upcomingEvents = spaceEventRepository.findUpcomingEvents(now);

            return upcomingEvents.stream()
                    .limit(10) // Limit to next 10 events
                    .map(this::mapToLaunchResponse)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error fetching upcoming launches: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Get events for a specific city
     */
    public CityEventsResponse getEventsForCity(CityEventsRequest request) {
        try {
            // Get upcoming launches
            List<LaunchResponse> upcomingLaunches = getUpcomingLaunches();

            // Get missed launches (last 7 days)
            List<LaunchResponse> missedLaunches = getMissedLaunches(7);

            // For now, nearby launches is same as upcoming (can be enhanced with distance
            // calculation)
            List<LaunchResponse> nearbyLaunches = upcomingLaunches.stream()
                    .limit(5)
                    .collect(Collectors.toList());

            // Get visible events for the city
            List<String> visibleEventNames = cityEventVisibilityRepository
                    .findVisibleEventsForCity(request.getCity(), request.getCountry())
                    .stream()
                    .map(v -> v.getSpaceEvent().getEventName())
                    .collect(Collectors.toList());

            String nextEventIn = calculateNextEventCountdown(upcomingLaunches);

            return CityEventsResponse.builder()
                    .city(request.getCity())
                    .country(request.getCountry())
                    .latitude(request.getLatitude())
                    .longitude(request.getLongitude())
                    .upcomingLaunches(upcomingLaunches)
                    .nearbyLaunches(nearbyLaunches)
                    .missedLaunches(missedLaunches)
                    .visibleEvents(visibleEventNames)
                    .totalEvents(upcomingLaunches.size() + missedLaunches.size())
                    .nextEventIn(nextEventIn)
                    .build();

        } catch (Exception e) {
            log.error("Error fetching events for city {}: {}", request.getCity(), e.getMessage());
            return CityEventsResponse.builder()
                    .city(request.getCity())
                    .country(request.getCountry())
                    .upcomingLaunches(new ArrayList<>())
                    .missedLaunches(new ArrayList<>())
                    .nearbyLaunches(new ArrayList<>())
                    .visibleEvents(new ArrayList<>())
                    .totalEvents(0)
                    .build();
        }
    }

    /**
     * Map SpaceEvent entity to LaunchResponse DTO
     */
    private LaunchResponse mapToLaunchResponse(SpaceEvent event) {
        String countdown = event.getStartTime().isBefore(LocalDateTime.now())
                ? "Launched " + formatPastTime(event.getStartTime())
                : formatCountdown(event.getStartTime());

        return LaunchResponse.builder()
                .id(event.getEventId())
                .name(event.getEventName())
                .launchTime(event.getStartTime())
                .status(event.getStatus())
                .rocketName(event.getRocketName())
                .location(event.getLocation())
                .videoUrl(event.getVideoUrl())
                .streamUrl(event.getStreamUrl())
                .countdown(countdown)
                .imageUrl(event.getImageUrl())
                .wasSuccessful(event.getWasSuccessful())
                .viewCount(event.getViewCount())
                .likesCount(event.getLikesCount())
                .build();
    }

    /**
     * Format time since launch (for past events)
     */
    private String formatPastTime(LocalDateTime launchTime) {
        Duration duration = Duration.between(launchTime, LocalDateTime.now());

        long days = duration.toDays();
        long hours = duration.toHours() % 24;

        if (days > 0) {
            return String.format("%d days ago", days);
        } else if (hours > 0) {
            return String.format("%d hours ago", hours);
        } else {
            return "today";
        }
    }

    /**
     * Format countdown for upcoming events
     */
    private String formatCountdown(LocalDateTime launchTime) {
        Duration duration = Duration.between(LocalDateTime.now(), launchTime);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        if (days > 0) {
            return String.format("T-%d days %d hours", days, hours);
        } else if (hours > 0) {
            return String.format("T-%d hours %d minutes", hours, minutes);
        } else {
            return String.format("T-%d minutes", minutes);
        }
    }

    /**
     * Calculate countdown to next event
     */
    private String calculateNextEventCountdown(List<LaunchResponse> launches) {
        if (launches.isEmpty()) {
            return "No upcoming events";
        }

        LaunchResponse nextLaunch = launches.get(0);
        return formatCountdown(nextLaunch.getLaunchTime());
    }
}
