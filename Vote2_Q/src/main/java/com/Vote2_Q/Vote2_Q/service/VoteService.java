package com.Vote2_Q.Vote2_Q.service;

import com.Vote2_Q.Vote2_Q.model.Vote;
import com.Vote2_Q.Vote2_Q.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository repository;

    public int getTotalVotesByReviewId(String reviewId){
        List<Vote> list;
        list = repository.findId(reviewId);
        int sizeList = list.size();
        int votes = 0;
        for (int i=0; i<sizeList; i++){
            if(list.get(i).isVote()){
                votes++;
            }
        }
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

    public Vote getVoteById(String voteId){
        return repository.findVoteById(voteId);
    }
}
