package com.Vote2_Q.Vote2_Q.service;


import com.Vote2_Q.Vote2_Q.Interefaces.repositories.TemporaryVoteRepository;
import com.Vote2_Q.Vote2_Q.model.TemporaryVote;
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
        TemporaryVote tv = repository.getTemporaryVoteById(tempVoteId);
        if(tv != null){
            System.out.println(tv);
            return tv;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no temporary votes");
        }
    }

    public void updateDataBaseTempVote(String tempVote) {
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
