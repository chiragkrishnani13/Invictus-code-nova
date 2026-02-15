package com.hackathon_iste.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "city_event_visibility")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityEventVisibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visibility_id")
    private Long visibilityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private SpaceEvent spaceEvent;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String country;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(name = "visible_from")
    private LocalDateTime visibleFrom;

    @Column(name = "visible_to")
    private LocalDateTime visibleTo;
}
