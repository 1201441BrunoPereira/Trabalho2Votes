package com.Vote1_Q.Vote1_Q.controller;


import com.Vote1_Q.Vote1_Q.model.Vote;
import com.Vote1_Q.Vote1_Q.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/votes")
public class VoteController {
    @Autowired
    private VoteService service;

    @GetMapping(value = "/")
    public List<Vote> getAllVotes(){return service.getAllVotes();}

    @GetMapping(value = "/{uuid}/")
    public int getTotalVotesByReviewId(@PathVariable("uuid") final UUID uuidReview ) throws IOException, InterruptedException {
        return service.getTotalVotesByReviewId(uuidReview);
    }

    @GetMapping(value = "/{uuid}/{userId}")
    public Vote getVoteByReviewIdAndUserId(@PathVariable("uuid") final UUID uuidReview , @PathVariable("userId") final Long userId ){
        return service.getVoteByReviewIdAndUserId(uuidReview,userId);
    }


}
