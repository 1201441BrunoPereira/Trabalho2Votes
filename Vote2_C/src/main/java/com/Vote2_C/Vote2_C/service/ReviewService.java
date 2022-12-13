package com.Vote2_C.Vote2_C.service;

import com.Vote2_C.Vote2_C.Interfaces.repositories.ReviewRepository;
import com.Vote2_C.Vote2_C.model.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void saveReview(String review){
        ReviewDTO reviewDTO = ReviewDTO.readJson(review);
        reviewRepository.save(reviewDTO);
    }

    public void deleteReview(String review){
        ReviewDTO reviewDTO = ReviewDTO.readJson(review);
        reviewRepository.delete(reviewDTO);
    }
}
