package com.Vote1_C.Vote1_C.Interfaces.RabbitMQ;

import com.Vote1_C.Vote1_C.service.ReviewService;
import com.Vote1_C.Vote1_C.service.TemporaryVoteService;
import com.Vote1_C.Vote1_C.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private TemporaryVoteService temporaryVoteService;


    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String vote) throws JsonProcessingException {
        voteService.createVote(vote);
        System.out.println("Creating new vote in Database with id:" + vote);
    }


    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consumeJsonMessageCreateReview(String review) throws JSONException {
        reviewService.saveReview(review,false);
        System.out.println("Review created:" + review);
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consumeJsonMessageDeleteReview(String review){
        reviewService.deleteReview(review);
        System.out.println("Review created:" + review);
    }

    @RabbitListener(queues = "#{autoDeleteQueue3.name}")
    public void consumeJsonMessageApproveReview(String review) throws JSONException, JsonProcessingException {
        reviewService.saveReview(review,true);
        temporaryVoteService.createFromTemp(review);
        System.out.println("Review created:" + review);
    }

    @RabbitListener(queues = "#{autoDeleteQueue4.name}")
    public void consumeJsonMessageToDeleteVoteFromReview(String tempVoteId) throws JsonProcessingException {
        temporaryVoteService.deleteFromTemp(tempVoteId);
        System.out.println("Delete temp vote:" + tempVoteId);
    }



}
