package com.Vote1_Q.Vote1_Q.service;

import com.Vote1_Q.Vote1_Q.model.Vote;
import com.Vote1_Q.Vote1_Q.Interfaces.repositories.VoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    public Vote getVoteByReviewIdAndUserId(String reviewId, Long userId){
        Vote existVote = repository.findReviewIdAndUserId(reviewId, userId);
        if(existVote != null){
            return existVote;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public List<Vote> getAllVotes(){
        return repository.findAllVotes();
    }

    public Vote getVoteById(String voteId){
        return repository.findVoteById(voteId);
    }

    public void createVote(String vote) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vote vt = objectMapper.readValue(vote, Vote.class);
        repository.save(vt);
    }

    public void updateDataBaseVote(String vote) {
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
