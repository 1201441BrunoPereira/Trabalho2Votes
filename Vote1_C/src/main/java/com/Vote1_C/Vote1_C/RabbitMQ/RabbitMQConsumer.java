package com.Vote1_C.Vote1_C.RabbitMQ;

import com.Vote1_C.Vote1_C.model.Review;
import com.Vote1_C.Vote1_C.repositories.ReviewRepository;
import com.Vote1_C.Vote1_C.repositories.ReviewRepositoryHttp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitMQConsumer {

    @Autowired
    private ReviewRepository reviewRepository;

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consumeJsonMessageAprrove(String isApproved) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Review rv = objectMapper.readValue(isApproved, Review.class);
        reviewRepository.save(rv);
        System.out.println("Review approved:" + rv.getReviewId());
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consumeJsonMessageCreateReview(String reviewId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String rv = objectMapper.readValue(reviewId, String.class);
        Review rev = new Review(rv, false);
        reviewRepository.save(rev);
        System.out.println("Review created:" + rev.getReviewId());
    }
}
