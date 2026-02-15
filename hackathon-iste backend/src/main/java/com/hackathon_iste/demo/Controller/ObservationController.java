package com.hackathon_iste.demo.Controller;

import com.hackathon_iste.demo.Service.ImageUploadService;
import com.hackathon_iste.demo.dto.ObservationRequest;
import com.hackathon_iste.demo.model.Observation;
import com.hackathon_iste.demo.Service.ObservationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
@RequiredArgsConstructor
@CrossOrigin
public class ObservationController {

    private final ObservationService service;

    private  final ImageUploadService imageUploadService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Observation create(
            @RequestPart("data") ObservationRequest req,
            @RequestPart("image") MultipartFile image,
            HttpServletRequest request
    ) {
        Long userId = (Long) request.getAttribute("userId");

        String imageUrl = imageUploadService.upload(image);
        req.setPhotoUrl(imageUrl);

        return service.create(req, userId);
    }


    @GetMapping
    public List<Observation> getAll() {
        return service.getAll();
    }
    @PostMapping("/{id}/view")
    public void incrementView(@PathVariable Long id) {
        service.incrementViewCount(id);
    }

}
