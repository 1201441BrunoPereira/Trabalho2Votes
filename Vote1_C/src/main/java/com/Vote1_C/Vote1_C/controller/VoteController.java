package com.Vote1_C.Vote1_C.controller;


import com.Vote1_C.Vote1_C.model.Vote;
import com.Vote1_C.Vote1_C.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    private VoteService service;

    @PostMapping(value = "/updateVote")
    public Vote upVoteReview(@RequestBody final Vote vote ) throws IOException, InterruptedException {
        boolean voteReviewApproved = service.voteReviewApproved(vote);
        if(voteReviewApproved){
            return service.updateVoteReview(vote);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This review isn't approved yet");
        }
    }


}
