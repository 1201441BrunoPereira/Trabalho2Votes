package com.Vote1_Q.Vote1_Q.Interfaces.RabbitMQ;

import com.Vote1_Q.Vote1_Q.service.TemporaryVoteService;
import com.Vote1_Q.Vote1_Q.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public void consumeJsonMessageToDeleteTempVote(String tempVoteId){
        temporaryVoteService.deleteFromTemp(tempVoteId);
        System.out.println("Delete temp vote:" + tempVoteId);
    }

    @RabbitListener(queues = "#{autoDeleteQueue4.name}")
    public void consumeJsonMessageToDeleteVoteFromReview(String tempVoteId){
        tempVoteId = tempVoteId.substring(1, tempVoteId.length() - 1);
        temporaryVoteService.deleteFromTemp(tempVoteId);
        System.out.println("Delete temp vote:" + tempVoteId);
    }

}
