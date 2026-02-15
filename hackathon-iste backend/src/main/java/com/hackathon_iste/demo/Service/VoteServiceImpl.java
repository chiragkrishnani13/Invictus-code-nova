package com.hackathon_iste.demo.Service;

import com.hackathon_iste.demo.dto.VoteRequest;
import com.hackathon_iste.demo.model.Observation;
import com.hackathon_iste.demo.model.ObservationVote;
import com.hackathon_iste.demo.Repository.ObservationRepository;
import com.hackathon_iste.demo.Repository.ObservationVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final ObservationVoteRepository voteRepo;
    private final ObservationRepository obsRepo;

    @Override
    public void vote(VoteRequest req) {

        Observation observation = obsRepo.findById(req.getObservationId())
                .orElseThrow(() -> new RuntimeException("Observation not found"));

        ObservationVote.VoteType newType =
                ObservationVote.VoteType.valueOf(req.getVoteType());

        voteRepo.findByObservationIdAndUserId(
                req.getObservationId(), req.getUserId()
        ).ifPresentOrElse(existingVote -> {

            // If same vote → do nothing
            if (existingVote.getVoteType() == newType) return;

            // Change vote
            if (existingVote.getVoteType() == ObservationVote.VoteType.UPVOTE) {
                observation.setUpvotes(observation.getUpvotes() - 1);
            } else {
                observation.setDownvotes(observation.getDownvotes() - 1);
            }

            existingVote.setVoteType(newType);
            voteRepo.save(existingVote);

        }, () -> {
            // New vote
            ObservationVote vote = ObservationVote.builder()
                    .observationId(req.getObservationId())
                    .userId(req.getUserId())
                    .voteType(newType)
                    .build();

            voteRepo.save(vote);
        });

        // Apply new vote count
        if (newType == ObservationVote.VoteType.UPVOTE) {
            if (observation.getUpvotes() == null) observation.setUpvotes(0);
            observation.setUpvotes(observation.getUpvotes() + 1);
        } else {
            if (observation.getDownvotes() == null) observation.setDownvotes(0);
            observation.setDownvotes(observation.getDownvotes() + 1);
        }

        obsRepo.save(observation);
    }
}
