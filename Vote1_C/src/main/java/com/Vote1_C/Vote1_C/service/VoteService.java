package com.Vote1_C.Vote1_C.service;


import com.Vote1_C.Vote1_C.model.Vote;
import com.Vote1_C.Vote1_C.repositories.ReviewRepository;
import com.Vote1_C.Vote1_C.repositories.Vote2Repository;
import com.Vote1_C.Vote1_C.repositories.VoteRepository;
import com.Vote1_C.Vote1_C.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private Vote2Repository vote2Repository;

    public boolean voteReviewApproved (Vote vote) throws IOException, InterruptedException {
        return reviewRepository.isApproved(vote.getUuidReview());
    }

    public Vote updateVoteReview(Vote vote) throws IOException, InterruptedException {
        Long userId;
        try{
            userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"You are not logged");
        }
        Vote existVote = repository.findReviewIdAndUserId(vote.getUuidReview(), userId);
        boolean existVote2 = vote2Repository.existVote(vote.getUuidReview(), userId);
        if(existVote == null && existVote2){
            vote.setUserId(userId);
            return repository.save(vote);
        }
        else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"You have already voted on this review");
        }
    }


}
