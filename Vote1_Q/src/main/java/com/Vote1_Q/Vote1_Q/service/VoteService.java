package com.Vote1_Q.Vote1_Q.service;


import com.Vote1_Q.Vote1_Q.model.Vote;
import com.Vote1_Q.Vote1_Q.repositories.Vote2Repository;
import com.Vote1_Q.Vote1_Q.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    @Autowired
    private Vote2Repository vote2Repository;


    public int getTotalVotesByReviewId(String reviewId) throws IOException, InterruptedException {
        List<Vote> list = new ArrayList<>();
        int votesAPI2 = vote2Repository.getTotalVotesByReviewId(reviewId);
        list = repository.findId(reviewId);
        int sizeList = list.size();
        int votes = 0;
        for (int i=0; i<sizeList; i++){
            if(list.get(i).isVote()){
                votes++;
            }
        }
        votes = votes + votesAPI2;
        return votes;
    }

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

}
