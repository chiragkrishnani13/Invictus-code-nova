package com.hackathon_iste.demo.Service;

import com.hackathon_iste.demo.dto.ObservationRequest;
import com.hackathon_iste.demo.model.Observation;
import java.util.List;

public interface ObservationService {
    Observation create(ObservationRequest req, Long userId);
    List<Observation> getAll();
    void incrementViewCount(Long observationId);

}
