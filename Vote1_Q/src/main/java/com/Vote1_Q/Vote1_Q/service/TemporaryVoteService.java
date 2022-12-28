package com.Vote1_Q.Vote1_Q.service;

import com.Vote1_Q.Vote1_Q.Interfaces.repositories.TemporaryVoteRepository;
import com.Vote1_Q.Vote1_Q.model.TemporaryVote;
import com.Vote1_Q.Vote1_Q.model.Vote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
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
        System.out.println("Deleting vote with id: " + tempVoteId + "because selected product does not exist");
        TemporaryVote temporaryVote = repository.getTemporaryVoteById(tempVoteId);
        if(temporaryVote != null){
            repository.delete(temporaryVote);
        }
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

    public void updateDataBaseTempVote(String tempVote) throws JsonProcessingException {
        try{
            JSONArray array = new JSONArray(tempVote);

            for(int i = 0; i < array.length(); i++) {
                JSONObject jsonObject1 = array.getJSONObject(i);

                ObjectMapper objectMapper = new ObjectMapper();
                TemporaryVote vt = objectMapper.readValue(jsonObject1.toString(), TemporaryVote.class);
                System.out.println("VT: " + vt.getId());
                repository.save(vt);
            }

        }catch(Exception e) {
            System.out.println("Error in Result as " + e.toString());
        }
    }

}
