package com.hackathon_iste.demo.Repository;

import com.hackathon_iste.demo.model.ObservationVote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ObservationVoteRepository
        extends JpaRepository<ObservationVote, Long> {

    Optional<ObservationVote> findByObservationIdAndUserId(
            Long observationId, Long userId
    );
}
