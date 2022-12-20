package com.RecoveryVoteQ.RecoveryVoteQ.controller.RabbitMQ;

import com.RecoveryVoteQ.RecoveryVoteQ.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private VoteService voteService;

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String vote) throws JsonProcessingException {
        voteService.createVote(vote);
        System.out.println("Creating new vote in Database with id:" + vote);
    }

    @RabbitListener(queues = "voteQRecovery.request")
    public String voteRecovery(String message) throws JsonProcessingException {
        System.out.println(" [x] Received request for vote recovery");
        return voteService.getVotes();
    }

}
