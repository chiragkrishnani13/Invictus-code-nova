package com.hackathon_iste.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityEventsRequest {

    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
}
