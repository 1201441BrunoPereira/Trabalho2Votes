package com.Vote1_C.Vote1_C.RabbitMQ;

import com.Vote1_C.Vote1_C.model.Review;
import com.Vote1_C.Vote1_C.repositories.ReviewRepository;
import com.Vote1_C.Vote1_C.repositories.ReviewRepositoryHttp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class RabbitMQConsumer {

    @Autowired
    private ReviewRepository reviewRepository;

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consumeJsonMessageAprrove(String isApproved) throws JsonProcessingException {
        Review rv = reviewRepository.findByReviewId(isApproved);
        rv.setApproved(true);
        reviewRepository.save(rv);
        System.out.println("Review approved:" + rv.getReviewId());
    }

    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
    public void consumeJsonMessageCreateReview(String reviewId) throws JsonProcessingException, UnsupportedEncodingException {
        String rv = reviewId;
        final Review rev = Review.newReview(reviewId, false);
        reviewRepository.save(rev);
        System.out.println("Review created:" + rv);
    }
}
