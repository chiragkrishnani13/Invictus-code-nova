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
public class LaunchReplayResponse {

    private LaunchResponse launch;

    private List<String> replayVideos; // YouTube links to watch replay
    private List<String> highlights; // Key moments/updates

    private String summary;
    private Boolean wasSuccessful;

    private String photoGalleryUrl; // Link to photo gallery
    private Integer photosCount;

    // User engagement
    private Integer viewCount;
    private Integer likesCount;
}
