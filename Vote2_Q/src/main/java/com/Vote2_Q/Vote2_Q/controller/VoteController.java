package com.Vote2_Q.Vote2_Q.controller;

import com.Vote2_Q.Vote2_Q.model.Vote;
import com.Vote2_Q.Vote2_Q.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    private VoteService service;

    @GetMapping(value = "/")
    public List<Vote> getAllVotes(){return service.getAllVotes();}

    @GetMapping(value = "/{reviewId}/")
    public int getTotalVotesByReviewId(@PathVariable("reviewId") final String reviewId ){
        return service.getTotalVotesByReviewId(reviewId);
    }

    @GetMapping(value = "/{reviewId}/{userId}")
    public Vote getVoteByReviewIdAndUserId(@PathVariable("reviewId") final String reviewId , @PathVariable("userId") final Long userId ){
        return service.getVoteByReviewIdAndUserId(reviewId,userId);
    }

    @GetMapping(value = "/{voteId}")
    public Vote getVoteById(@PathVariable("voteId") final String voteId){
        return service.getVoteById(voteId);
    }

}
