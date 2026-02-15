package com.hackathon_iste.demo.dto;

import lombok.Data;

@Data
public class ObservationRequest {

    private Long userId;
    private String eventType;
    private String title;
    private String description;
    private Double latitude;
    private Double longitude;
    private String locationName;
    private String photoUrl;
    private String verificationMethod;
    private String verificationDetails;
    private String satelliteName;
    private Integer satelliteNoradId;
}
