package com.Vote2_Q.Vote2_Q.Interefaces.RabbitMQ;

import com.Vote2_Q.Vote2_Q.model.Vote;
import com.Vote2_Q.Vote2_Q.Interefaces.repositories.VoteRepository;
import com.Vote2_Q.Vote2_Q.service.TemporaryVoteService;
import com.Vote2_Q.Vote2_Q.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private TemporaryVoteService temporaryVoteService;
    @Autowired
    private VoteService voteService;

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String vote) throws JsonProcessingException {
        voteService.createVote(vote);
        System.out.println("Creating new vote in Database with id:" + vote);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consumeJsonMessageToCreateTempVote(String tempVote) throws JsonProcessingException {
        temporaryVoteService.createTempVote(tempVote);
        System.out.println("create temp vote:" + tempVote);
    }

    @RabbitListener(queues = "#{autoDeleteQueue3.name}")
    public void consumeJsonMessageToDeleteVoteFromReview(String tempVoteId){
        temporaryVoteService.deleteFromTemp(tempVoteId);
        System.out.println("Delete temp vote:" + tempVoteId);
    }

}
