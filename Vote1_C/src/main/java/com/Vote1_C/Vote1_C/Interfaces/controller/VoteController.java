package com.Vote1_C.Vote1_C.Interfaces.controller;

import com.Vote1_C.Vote1_C.model.Vote;
import com.Vote1_C.Vote1_C.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    private VoteService service;

    @PostMapping(value = "/updateVote")
    public ResponseEntity<Vote> upVoteReview(@RequestBody final Vote vote ) throws JsonProcessingException {
        boolean voteReviewApproved = service.voteReviewApproved(vote);
        if(voteReviewApproved){
            return new ResponseEntity<>(service.updateVoteReview(vote), HttpStatus.CREATED);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This review isn't approved yet");
        }
    }


}
