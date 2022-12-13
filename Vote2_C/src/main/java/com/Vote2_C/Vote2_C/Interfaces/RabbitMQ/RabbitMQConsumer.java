package com.Vote2_C.Vote2_C.Interfaces.RabbitMQ;

import com.Vote2_C.Vote2_C.Interfaces.repositories.ReviewRepository;
import com.Vote2_C.Vote2_C.Interfaces.repositories.VoteRepository;
import com.Vote2_C.Vote2_C.model.ReviewDTO;
import com.Vote2_C.Vote2_C.model.Vote;
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

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String vote) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vote vt = objectMapper.readValue(vote, Vote.class);
        voteRepository.save(vt);
        System.out.println("Creating new vote in Database with id:" + vt.getId());
    }


    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consumeJsonMessageCreateReview(String review){
        ReviewDTO reviewDTO = ReviewDTO.readJson(review);
        reviewRepository.save(reviewDTO);
        System.out.println("Review created:" + reviewDTO.getReviewId());
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consumeJsonMessageDeleteReview(String review){
        ReviewDTO reviewDTO = ReviewDTO.readJson(review);
        reviewRepository.delete(reviewDTO);
        System.out.println("Review deleted:" + reviewDTO.getReviewId());
    }

    @RabbitListener(queues = "#{autoDeleteQueue3.name}")
    public void consumeJsonMessageApproveReview(String review){
        ReviewDTO reviewDTO = ReviewDTO.readJson(review);
        reviewRepository.save(reviewDTO);
        System.out.println("Review change status:" + reviewDTO.getReviewId());
    }
}
