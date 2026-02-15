package com.hackathon_iste.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "observation_votes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"observation_id", "user_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObservationVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "observation_id")
    private Long observationId;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum VoteType {
        UPVOTE, DOWNVOTE
    }
}
