package com.Vote1_C.Vote1_C.RabbitMQ;

import com.Vote1_C.Vote1_C.model.Review;
import com.Vote1_C.Vote1_C.repositories.ReviewRepository;
import com.Vote1_C.Vote1_C.repositories.ReviewRepositoryHttp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RabbitMQConsumer {

    @Autowired
    private ReviewRepository reviewRepository;

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

    @RabbitListener(queues = "#{autoDeleteQueue4.name}")
    public void consumeJsonMessageCreateReview2(String review) {
        ReviewDTO reviewDTO = ReviewDTO.readJson(review);
        reviewRepository.save(reviewDTO);
        System.out.println("Review created:" + reviewDTO.getReviewId());
    }

    @RabbitListener(queues = "#{autoDeleteQueue5.name}")
    public void consumeJsonMessageDeleteReview2(String review){
        ReviewDTO reviewDTO = ReviewDTO.readJson(review);
        reviewRepository.delete(reviewDTO);
        System.out.println("Review deleted:" + reviewDTO.getReviewId());
    }

    @RabbitListener(queues = "#{autoDeleteQueue6.name}")
    public void consumeJsonMessageApproveReview2(String review){
        ReviewDTO reviewDTO = ReviewDTO.readJson(review);
        reviewRepository.save(reviewDTO);
        System.out.println("Review change status:" + reviewDTO.getReviewId());
    }


}
