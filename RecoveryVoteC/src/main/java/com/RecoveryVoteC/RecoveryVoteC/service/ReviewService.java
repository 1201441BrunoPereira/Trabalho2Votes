package com.RecoveryVoteC.RecoveryVoteC.service;

import com.RecoveryVoteC.RecoveryVoteC.model.Review;
import com.RecoveryVoteC.RecoveryVoteC.repositories.ReviewRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void saveReview(String rev){
        Review review = Review.readJson(rev);
        reviewRepository.save(review);
    }

    public void deleteReview(String rev){
        Review review = Review.readJson(rev);
        reviewRepository.delete(review);
    }

    public String getReviews() throws JsonProcessingException {
        List<Review> reviewList = reviewRepository.getAllReviews();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(reviewList);
        System.out.println(" Review:  " + json);
        return json;
    }
}
