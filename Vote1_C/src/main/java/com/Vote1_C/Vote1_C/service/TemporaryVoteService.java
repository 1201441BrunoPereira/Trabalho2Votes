package com.Vote1_C.Vote1_C.service;

import com.Vote1_C.Vote1_C.Interfaces.RabbitMQ.RabbitMQPublisher;
import com.Vote1_C.Vote1_C.Interfaces.repositories.TemporaryVoteRepository;
import com.Vote1_C.Vote1_C.Interfaces.repositories.VoteRepository;
import com.Vote1_C.Vote1_C.VoteDTO;
import com.Vote1_C.Vote1_C.model.TemporaryVote;
import com.Vote1_C.Vote1_C.model.Vote;
import com.Vote1_C.Vote1_C.security.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
    private VoteRepository voteRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private EmailConfigImpl emailConfig;

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

    public void createFromTemp(String review) throws JSONException, JsonProcessingException {
        JSONObject object = new JSONObject(review);
        String voteId = object.getString("voteIdIfCreatedFromVote");
        if(voteId != null){
            TemporaryVote temporaryVote = repository.getTemporaryVoteById(voteId);
            if(reviewService.checkStatusReview(review)){
                Vote vote = new Vote();
                vote.setId(Vote.generateUUID());
                vote.setVote(temporaryVote.isVote());
                vote.setUserId(temporaryVote.getUserId());
                vote.setReviewId(object.getString("reviewId"));
                voteRepository.save(vote);
                repository.delete(temporaryVote);
                jsonProducer.sendJsonMessage(vote);
            }else{
                repository.delete(temporaryVote);
            }

        }
    }

    public void deleteFromTemp(String tempVoteId) throws JSONException, JsonProcessingException {
        tempVoteId = tempVoteId.substring(1, tempVoteId.length() - 1);
        System.out.println("vote: " + tempVoteId);
        if(tempVoteId != null){
            TemporaryVote temporaryVote = repository.getTemporaryVoteById(tempVoteId);
            emailConfig.sendSimpleMail("giovannafantacini@gmail.com","Erro criação review","Fail");
            repository.delete(temporaryVote);
        }
    }

}
