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
        jsonProducer.sendJsonMessageToCreateTempVote(tempVote);
        return tempVote;
    }

    public void createFromTemp(String review) throws JSONException, JsonProcessingException {
        JSONObject object = new JSONObject(review);
        if(!object.isNull("voteIdIfCreatedFromVote")){
            TemporaryVote temporaryVote = repository.getTemporaryVoteById(object.getString("voteIdIfCreatedFromVote"));
            if(reviewService.checkStatusReview(review) && temporaryVote != null){
                Vote vote = new Vote();
                vote.setId(Vote.generateUUID());
                vote.setVote(temporaryVote.isVote());
                vote.setUserId(temporaryVote.getUserId());
                vote.setReviewId(object.getString("reviewId"));
                voteRepository.save(vote);
                jsonProducer.sendJsonMessageToDeleteTempVote(temporaryVote.getId());
                repository.delete(temporaryVote);
                jsonProducer.sendJsonMessage(vote);
                emailConfig.sendSimpleMail("1201441@isep.ipp.pt","Your vote  was automatic created ","Vote Status");
            }else {
                if (temporaryVote != null){
                    jsonProducer.sendJsonMessageToDeleteTempVote(temporaryVote.getId());
                    repository.delete(temporaryVote);
                    emailConfig.sendSimpleMail("1201441@isep.ipp.pt", "Your vote  was deleted because your review was rejected ", "Vote Status");
                }
            }

        }
    }

    public void deleteFromTemp(String tempVoteId) throws JsonProcessingException {
        tempVoteId = tempVoteId.substring(1, tempVoteId.length() - 1);
        System.out.println("Deleting vote with id: " + tempVoteId + "because selected product does not exist");
        if(repository.getTemporaryVoteById(tempVoteId) != null){
            TemporaryVote temporaryVote = repository.getTemporaryVoteById(tempVoteId);
            emailConfig.sendSimpleMail("1201441@isep.ipp.pt","Erro criação review, o produto escolhido não existe","Fail to create vote");
            repository.delete(temporaryVote);
        }

    }

}
