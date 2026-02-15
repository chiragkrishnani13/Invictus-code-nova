package com.hackathon_iste.demo.Service;

import com.hackathon_iste.demo.dto.ObservationRequest;
import com.hackathon_iste.demo.model.Observation;
import com.hackathon_iste.demo.Repository.ObservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObservationServiceImpl implements ObservationService {

    private final ObservationRepository repo;


    // 🔥 UPDATED METHOD (userId token se aayega)
    @Override
    public Observation create(ObservationRequest req, Long userId) {

        Observation obs = new Observation();

        // 🔹 USER (JWT TOKEN SE)
        obs.setUserId(userId);

        // 🔹 EVENT
        obs.setEventType(Observation.EventType.valueOf(req.getEventType()));
        obs.setTitle(req.getTitle());
        obs.setDescription(req.getDescription());

        // 🔹 LOCATION
        obs.setLatitude(req.getLatitude());
        obs.setLongitude(req.getLongitude());
        obs.setLocationName(req.getLocationName());

        // 🔹 MEDIA
        obs.setPhotoUrl(req.getPhotoUrl());

        // 🔹 VERIFICATION (OPTIONAL)
        obs.setVerificationMethod(req.getVerificationMethod());
        obs.setVerificationDetails(req.getVerificationDetails());

        // 🔹 SATELLITE (OPTIONAL)
        obs.setSatelliteName(req.getSatelliteName());
        obs.setSatelliteNoradId(req.getSatelliteNoradId());

        // ❌ observedAt / createdAt / updatedAt yahan set nahi karna
        // ✔️ @PrePersist entity automatically handle karega

        return repo.save(obs);
    }

    @Override
    public List<Observation> getAll() {
        return repo.findAll();
    }
    @Override
    public void incrementViewCount(Long observationId) {

        Observation obs = repo.findById(observationId)
                .orElseThrow(() -> new RuntimeException("Observation not found"));

        if (obs.getViewCount() == null) {
            obs.setViewCount(0);
        }

        obs.setViewCount(obs.getViewCount() + 1);

        repo.save(obs);
    }

}
