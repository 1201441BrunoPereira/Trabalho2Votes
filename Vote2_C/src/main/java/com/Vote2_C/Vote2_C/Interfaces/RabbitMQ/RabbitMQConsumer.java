package com.Vote2_C.Vote2_C.Interfaces.RabbitMQ;

import com.Vote2_C.Vote2_C.Interfaces.repositories.ReviewRepository;
import com.Vote2_C.Vote2_C.Interfaces.repositories.VoteRepository;
import com.Vote2_C.Vote2_C.model.ReviewDTO;
import com.Vote2_C.Vote2_C.model.Vote;
import com.Vote2_C.Vote2_C.service.ReviewService;
import com.Vote2_C.Vote2_C.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private VoteRepository voteRepository;

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
}
