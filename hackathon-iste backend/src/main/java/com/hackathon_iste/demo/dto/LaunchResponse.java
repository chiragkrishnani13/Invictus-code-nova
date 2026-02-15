package com.hackathon_iste.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaunchResponse {

    private Long id;
    private String name;
    private LocalDateTime launchTime;
    private String status;
    private String rocketName;
    private String location;
    private String videoUrl;
    private String streamUrl;
    private String countdown;
    private String imageUrl;
    private Boolean wasSuccessful;

    // Engagement metrics
    private Integer viewCount;
    private Integer likesCount;
}
