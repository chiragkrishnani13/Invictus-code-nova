package com.hackathon_iste.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_replays")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventReplay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "replay_id")
    private Long replayId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private SpaceEvent spaceEvent;

    @Column(name = "replay_url_img", columnDefinition = "TEXT")
    private String replayUrlImg;

    @Column(length = 200)
    private String description;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    // Additional fields for enhanced replay functionality
    @Column(name = "video_url", columnDefinition = "TEXT")
    private String videoUrl;

    @Column(name = "thumbnail_url", columnDefinition = "TEXT")
    private String thumbnailUrl;

    @Column(name = "highlight_text", columnDefinition = "TEXT")
    private String highlightText;

    @Column(name = "photo_gallery_url", columnDefinition = "TEXT")
    private String photoGalleryUrl;

    @Column(name = "photos_count")
    private Integer photosCount;
}
