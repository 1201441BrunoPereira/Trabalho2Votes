package com.RecoveryVoteC.RecoveryVoteC.service;


import com.RecoveryVoteC.RecoveryVoteC.model.Vote;
import com.RecoveryVoteC.RecoveryVoteC.repositories.ReviewRepository;
import com.RecoveryVoteC.RecoveryVoteC.repositories.VoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;


    public void createVote(String vote) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vote vt = objectMapper.readValue(vote, Vote.class);
        repository.save(vt);
    }
}
