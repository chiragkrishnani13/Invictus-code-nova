package com.hackathon_iste.demo.dto;

import lombok.Data;

@Data
public class VoteRequest {

    private Long observationId;
    private Long userId;
    private String voteType; // UPVOTE or DOWNVOTE
}
