package com.Vote1_Q.Vote1_Q.Interfaces.controller;

import com.Vote1_Q.Vote1_Q.model.TemporaryVote;
import com.Vote1_Q.Vote1_Q.model.Vote;
import com.Vote1_Q.Vote1_Q.service.TemporaryVoteService;
import com.Vote1_Q.Vote1_Q.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    private VoteService service;

    @Autowired
    private TemporaryVoteService temporaryVoteService;

    @GetMapping(value = "/")
    public List<Vote> getAllVotes(){return service.getAllVotes();}

    @GetMapping(value = "/{reviewId}/{userId}")
    public Vote getVoteByReviewIdAndUserId(@PathVariable("reviewId") final String reviewId , @PathVariable("userId") final Long userId ){
        return service.getVoteByReviewIdAndUserId(reviewId,userId);
    }

    @GetMapping(value = "/{voteId}")
    public Vote getVoteById(@PathVariable("voteId") final String voteId){
        return service.getVoteById(voteId);
    }

    @GetMapping(value = "/tempVote")
    public List<TemporaryVote> getAllTempVote(){
       return temporaryVoteService.getAllTempVote();
    }

    @GetMapping(value = "/tempVote/{tempVoteId}")
    public TemporaryVote getTempVoteById(@PathVariable("tempVoteId") final String tempVoteId){
        return temporaryVoteService.getTempVoteById(tempVoteId);
    }

}
