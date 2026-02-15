package com.hackathon_iste.demo.Controller;

import com.hackathon_iste.demo.dto.VoteRequest;
import com.hackathon_iste.demo.Service.VoteService;
import com.hackathon_iste.demo.model.Observation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
@CrossOrigin
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public String vote(@RequestBody VoteRequest request) {
        voteService.vote(request);
        return "Vote recorded successfully";
    }



}
