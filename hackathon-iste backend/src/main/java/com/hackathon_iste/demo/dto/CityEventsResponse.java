package com.hackathon_iste.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityEventsResponse {

    private String city;
    private String country;
    private Double latitude;
    private Double longitude;

    private List<LaunchResponse> upcomingLaunches;
    private List<LaunchResponse> nearbyLaunches;
    private List<LaunchResponse> missedLaunches; // Events user missed

    private List<String> visibleEvents;

    private Integer totalEvents;
    private String nextEventIn;
}
