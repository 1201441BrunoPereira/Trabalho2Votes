package com.Vote1_Q.Vote1_Q.service;

import com.Vote1_Q.Vote1_Q.Interfaces.repositories.TemporaryVoteRepository;
import com.Vote1_Q.Vote1_Q.model.TemporaryVote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryVoteService {


    @Autowired
    private TemporaryVoteRepository repository;


    public void deleteFromTemp(String tempVoteId){
        TemporaryVote temporaryVote = repository.getTemporaryVoteById(tempVoteId);
        repository.delete(temporaryVote);

    }

    public void createTempVote(String tempVote) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TemporaryVote tv = objectMapper.readValue(tempVote, TemporaryVote.class);
        repository.save(tv);
    }

    public List<TemporaryVote> getAllTempVote(){
        if(repository.getAll() != null){
            return repository.getAll();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no temporary votes");
        }
    }

    public TemporaryVote getTempVoteById(String tempVoteId){
        if(repository.getTemporaryVoteById(tempVoteId) != null){
            return repository.getTemporaryVoteById(tempVoteId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no temporary votes");
        }
    }

}
