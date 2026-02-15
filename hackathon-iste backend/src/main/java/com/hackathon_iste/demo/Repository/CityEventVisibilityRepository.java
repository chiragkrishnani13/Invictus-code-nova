package com.hackathon_iste.demo.Repository;

import com.hackathon_iste.demo.model.CityEventVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityEventVisibilityRepository extends JpaRepository<CityEventVisibility, Long> {

    // Find visibility records for a specific city and country
    List<CityEventVisibility> findByCityAndCountry(String city, String country);

    // Find all events visible in a specific city
    @Query("SELECT v FROM CityEventVisibility v WHERE v.city = :city AND v.country = :country")
    List<CityEventVisibility> findVisibleEventsForCity(
            @Param("city") String city,
            @Param("country") String country);

    // Find visibility by event ID
    List<CityEventVisibility> findBySpaceEvent_EventId(Long eventId);
}
