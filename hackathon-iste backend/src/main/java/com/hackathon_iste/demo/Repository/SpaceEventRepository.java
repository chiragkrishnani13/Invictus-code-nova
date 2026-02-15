package com.hackathon_iste.demo.Repository;

import com.hackathon_iste.demo.model.SpaceEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpaceEventRepository extends JpaRepository<SpaceEvent, Long> {

    // Find events with replay available
    List<SpaceEvent> findByReplayAvailableTrue();

    // Find events by date range
    @Query("SELECT e FROM SpaceEvent e WHERE e.startTime BETWEEN :startDate AND :endDate ORDER BY e.startTime DESC")
    List<SpaceEvent> findEventsByDateRange(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    // Find past events (missed events)
    @Query("SELECT e FROM SpaceEvent e WHERE e.startTime < :currentTime ORDER BY e.startTime DESC")
    List<SpaceEvent> findPastEvents(@Param("currentTime") LocalDateTime currentTime);

    // Find upcoming events
    @Query("SELECT e FROM SpaceEvent e WHERE e.startTime > :currentTime ORDER BY e.startTime ASC")
    List<SpaceEvent> findUpcomingEvents(@Param("currentTime") LocalDateTime currentTime);

    // Find events by type
    List<SpaceEvent> findByEventType(String eventType);

    // Find events by status
    List<SpaceEvent> findByStatus(String status);
}
