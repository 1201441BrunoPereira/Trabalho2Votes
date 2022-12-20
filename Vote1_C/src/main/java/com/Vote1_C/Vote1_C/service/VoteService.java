package com.Vote1_C.Vote1_C.service;

import com.Vote1_C.Vote1_C.Interfaces.RabbitMQ.RabbitMQPublisher;
import com.Vote1_C.Vote1_C.model.Vote;
import com.Vote1_C.Vote1_C.Interfaces.repositories.ReviewRepository;
import com.Vote1_C.Vote1_C.Interfaces.repositories.VoteRepository;
import com.Vote1_C.Vote1_C.security.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RabbitMQPublisher jsonProducer;

    public boolean voteReviewApproved (Vote vote) {
        return reviewRepository.isVoted(vote.getReviewId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Review not found"));
    }

    public Vote updateVoteReview(Vote vote) throws JsonProcessingException {
        Long userId;
        try{
            userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"You are not logged");
        }
        Vote existVote = repository.findReviewIdAndUserId(vote.getReviewId(), userId);
        if(existVote == null){
            String id = vote.generateUUID();
            vote.setId(id);
            vote.setUserId(userId);
            jsonProducer.sendJsonMessage(vote);
            return repository.save(vote);
        }
        else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"You have already voted on this review");
        }
    }

    public void createVote(String vote) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vote vt = objectMapper.readValue(vote, Vote.class);
        repository.save(vt);
    }

    public void updateDataBaseVote(String vote) throws JsonProcessingException {
        try{
            JSONArray array = new JSONArray(vote);

            for(int i = 0; i < array.length(); i++) {
                JSONObject jsonObject1 = array.getJSONObject(i);

                ObjectMapper objectMapper = new ObjectMapper();
                Vote vt = objectMapper.readValue(jsonObject1.toString(), Vote.class);
                System.out.println("VT: " + vt.getId());
                repository.save(vt);
            }

        }catch(Exception e) {
            System.out.println("Error in Result as " + e.toString());
        }
    }
}
