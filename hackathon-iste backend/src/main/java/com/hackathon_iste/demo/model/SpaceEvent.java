package com.hackathon_iste.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "space_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpaceEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_name", nullable = false, length = 150)
    private String eventName;

    @Column(name = "event_type", length = 50)
    private String eventType;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "replay_available")
    @Builder.Default
    private Boolean replayAvailable = false;

    // Additional fields for enhanced functionality
    @Column(name = "rocket_name", length = 100)
    private String rocketName;

    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "video_url", columnDefinition = "TEXT")
    private String videoUrl;

    @Column(name = "stream_url", columnDefinition = "TEXT")
    private String streamUrl;

    @Column(name = "status", length = 50)
    private String status; // Success, Failed, Scheduled, etc.

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "was_successful")
    private Boolean wasSuccessful;

    // Engagement metrics
    @Builder.Default
    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Builder.Default
    @Column(name = "likes_count")
    private Integer likesCount = 0;

    // Relationships
    @OneToMany(mappedBy = "spaceEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventReplay> eventReplays;

    @OneToMany(mappedBy = "spaceEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CityEventVisibility> cityVisibilities;

    // Audit fields
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
