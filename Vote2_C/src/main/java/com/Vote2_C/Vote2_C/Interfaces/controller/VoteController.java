package com.Vote2_C.Vote2_C.Interfaces.controller;

import com.Vote2_C.Vote2_C.VoteDTO;
import com.Vote2_C.Vote2_C.model.TemporaryVote;
import com.Vote2_C.Vote2_C.model.Vote;
import com.Vote2_C.Vote2_C.service.TemporaryVoteService;
import com.Vote2_C.Vote2_C.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    private VoteService service;

    @Autowired
    private TemporaryVoteService tempVoteService;

    @PostMapping(value = "/updateVote")
    public ResponseEntity<Vote> upVoteReview(@RequestBody final Vote vote ) throws JsonProcessingException {
        boolean voteReviewApproved = service.voteReviewApproved(vote);
        if(voteReviewApproved){
            return new ResponseEntity<>(service.updateVoteReview(vote), HttpStatus.CREATED);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This review isn't approved yet");
        }
    }

    @PostMapping(value = "/voteProduct")
    public ResponseEntity<TemporaryVote> upVoteProduct(@RequestBody final VoteDTO vote ) throws JsonProcessingException {
        return new ResponseEntity<>(tempVoteService.updateVoteProduct(vote), HttpStatus.ACCEPTED);
    }


}
