package com.RecoveryVoteC.RecoveryVoteC.service;

import com.RecoveryVoteC.RecoveryVoteC.model.Review;
import com.RecoveryVoteC.RecoveryVoteC.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
