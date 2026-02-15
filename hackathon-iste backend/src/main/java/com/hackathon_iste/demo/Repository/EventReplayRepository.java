package com.hackathon_iste.demo.Repository;

import com.hackathon_iste.demo.model.EventReplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventReplayRepository extends JpaRepository<EventReplay, Long> {

    // Find all replays for a specific event
    List<EventReplay> findBySpaceEvent_EventId(Long eventId);
}
