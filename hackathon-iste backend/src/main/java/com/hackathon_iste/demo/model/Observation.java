package com.hackathon_iste.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "observations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 USER
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 🔹 EVENT
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    // 🔹 LOCATION
    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(name = "location_name")
    private String locationName;

    // 🔹 TIME
    @Column(name = "observed_at", nullable = false)
    private LocalDateTime observedAt;

    // 🔹 MEDIA
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private String photoUrl;

    // 🔹 ENGAGEMENT
    @Builder.Default
    private Integer upvotes = 0;

    @Builder.Default
    private Integer downvotes = 0;

    @Builder.Default
    private Integer viewCount = 0;

    // 🔹 VERIFICATION
    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    @Builder.Default
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @Column(name = "verification_method", length = 50)
    private String verificationMethod;

    @Column(name = "verification_details", columnDefinition = "TEXT")
    private String verificationDetails;

    // 🔹 SATELLITE (OPTIONAL)
    @Column(name = "satellite_name", length = 100)
    private String satelliteName;

    @Column(name = "satellite_norad_id")
    private Integer satelliteNoradId;

    // 🔹 SOFT DELETE
    @Builder.Default
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    // 🔹 AUDIT
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 🔥 LIFECYCLE CALLBACKS
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.observedAt = now;
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // 🔹 ENUMS
    public enum EventType {
        SATELLITE, METEOR, AURORA, PLANET, OTHER
    }

    public enum VerificationStatus {
        PENDING, VERIFIED, DISPUTED, REJECTED
    }
}
