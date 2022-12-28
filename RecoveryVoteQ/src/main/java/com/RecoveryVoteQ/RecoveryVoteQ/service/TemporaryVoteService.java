package com.RecoveryVoteQ.RecoveryVoteQ.service;

import com.RecoveryVoteQ.RecoveryVoteQ.model.TemporaryVote;
import com.RecoveryVoteQ.RecoveryVoteQ.model.Vote;
import com.RecoveryVoteQ.RecoveryVoteQ.repositories.TemporaryVoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public String getTempVotes(String message) throws JsonProcessingException {
        String pageNumber = message.substring(3);
        int page = Integer.parseInt(pageNumber);
        List<TemporaryVote> tempVoteList = repository.getAllByPage(PageRequest.of(page,10));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(tempVoteList);
        System.out.println(" Vote:  " + json);
        return json;
    }

}
