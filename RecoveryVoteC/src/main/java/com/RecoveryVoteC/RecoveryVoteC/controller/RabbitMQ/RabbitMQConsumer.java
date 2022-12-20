package com.RecoveryVoteC.RecoveryVoteC.controller.RabbitMQ;

import com.RecoveryVoteC.RecoveryVoteC.service.ReviewService;
import com.RecoveryVoteC.RecoveryVoteC.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private VoteService voteService;


    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String vote) throws JsonProcessingException {
        voteService.createVote(vote);
        System.out.println("Creating new vote in Database with id:" + vote);
    }


    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consumeJsonMessageCreateReview(String review){
        reviewService.saveReview(review);
        System.out.println("Review created:" + review);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consumeJsonMessageDeleteReview(String review){
        reviewService.deleteReview(review);
        System.out.println("Review created:" + review);
    }

    @RabbitListener(queues = "#{autoDeleteQueue3.name}")
    public void consumeJsonMessageApproveReview(String review){
        reviewService.saveReview(review);
        System.out.println("Review created:" + review);
    }

    @RabbitListener(queues = "voteCRecovery.request")
    public String voteRecovery(String message) throws JsonProcessingException {
        if(message.equals("Vote")){
            System.out.println(" [x] Received request for vote recovery");
            return voteService.getVotes();
        }else{
            System.out.println(" [x] Received request for review recovery");
            return reviewService.getReviews();
        }
    }
}
