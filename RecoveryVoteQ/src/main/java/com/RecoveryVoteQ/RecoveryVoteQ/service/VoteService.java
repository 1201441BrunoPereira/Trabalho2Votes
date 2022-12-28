package com.RecoveryVoteQ.RecoveryVoteQ.service;

import com.RecoveryVoteQ.RecoveryVoteQ.model.Vote;
import com.RecoveryVoteQ.RecoveryVoteQ.repositories.VoteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    public void createVote(String vote) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vote vt = objectMapper.readValue(vote, Vote.class);
        repository.save(vt);
    }

    public String getVotes(String message) throws JsonProcessingException {
        String pageNumber = message.substring(3);
        int page = Integer.parseInt(pageNumber);
        List<Vote> voteList = repository.getAllByPage(PageRequest.of(page,10));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(voteList);
        System.out.println(" Vote:  " + json);
        return json;
    }

}
