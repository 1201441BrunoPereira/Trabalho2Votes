package com.Vote1_C.Vote1_C.service;

import com.Vote1_C.Vote1_C.Interfaces.RabbitMQ.RabbitMQPublisher;
import com.Vote1_C.Vote1_C.Interfaces.repositories.ReviewRepository;
import com.Vote1_C.Vote1_C.Interfaces.repositories.TemporaryVoteRepository;
import com.Vote1_C.Vote1_C.VoteDTO;
import com.Vote1_C.Vote1_C.model.TemporaryVote;
import com.Vote1_C.Vote1_C.model.Vote;
import com.Vote1_C.Vote1_C.security.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TemporaryVoteService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TemporaryVoteRepository repository;

    @Autowired
    private RabbitMQPublisher jsonProducer;

    public TemporaryVote updateVoteProduct(VoteDTO vote) throws JsonProcessingException {
        Long userId;
        try{
            userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"You are not logged");
        }
        TemporaryVote tempVote = new TemporaryVote(vote.getVote(), userId);
        repository.save(tempVote);
        vote.setUserId(userId);
        vote.setId(tempVote.getId());
        System.out.println("vote: " + vote);
        jsonProducer.sendJsonMessageToReview(vote);
        return tempVote;
    }
}
