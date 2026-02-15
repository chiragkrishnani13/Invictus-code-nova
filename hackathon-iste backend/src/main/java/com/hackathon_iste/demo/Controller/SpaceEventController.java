package com.hackathon_iste.demo.Controller;

import com.hackathon_iste.demo.Service.SpaceEventService;
import com.hackathon_iste.demo.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/space-events")
@RequiredArgsConstructor
@CrossOrigin
public class SpaceEventController {

    private final SpaceEventService spaceEventService;

    /**
     * Get events for a specific city
     * POST /api/space-events/city
     */
    @PostMapping("/city")
    public ResponseEntity<CityEventsResponse> getEventsForCity(@RequestBody CityEventsRequest request) {
        CityEventsResponse response = spaceEventService.getEventsForCity(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get events by city name (simplified endpoint)
     * GET /api/space-events/city/{cityName}
     */
    @GetMapping("/city/{cityName}")
    public ResponseEntity<CityEventsResponse> getEventsByCityName(
            @PathVariable String cityName,
            @RequestParam(defaultValue = "India") String country) {
        CityEventsRequest request = CityEventsRequest.builder()
                .city(cityName)
                .country(country)
                .build();
        CityEventsResponse response = spaceEventService.getEventsForCity(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get missed launches (past events)
     * GET /api/space-events/missed?daysAgo=7
     */
    @GetMapping("/missed")
    public ResponseEntity<List<LaunchResponse>> getMissedLaunches(
            @RequestParam(defaultValue = "7") int daysAgo) {
        List<LaunchResponse> missedLaunches = spaceEventService.getMissedLaunches(daysAgo);
        return ResponseEntity.ok(missedLaunches);
    }

    /**
     * Get replay details for a specific launch
     * GET /api/space-events/replay/{eventId}
     */
    @GetMapping("/replay/{eventId}")
    public ResponseEntity<LaunchReplayResponse> getLaunchReplay(@PathVariable Long eventId) {
        LaunchReplayResponse replay = spaceEventService.getLaunchReplay(eventId);
        if (replay == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(replay);
    }

    /**
     * Get all events with replay available
     * GET /api/space-events/replays
     */
    @GetMapping("/replays")
    public ResponseEntity<List<LaunchResponse>> getAllEventsWithReplay() {
        List<LaunchResponse> events = spaceEventService.getAllEventsWithReplay();
        return ResponseEntity.ok(events);
    }

    /**
     * Get upcoming launches
     * GET /api/space-events/upcoming
     */
    @GetMapping("/upcoming")
    public ResponseEntity<List<LaunchResponse>> getUpcomingLaunches() {
        List<LaunchResponse> launches = spaceEventService.getUpcomingLaunches();
        return ResponseEntity.ok(launches);
    }
}
