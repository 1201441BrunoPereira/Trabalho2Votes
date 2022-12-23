package com.Vote1_C.Vote1_C.service;

import com.Vote1_C.Vote1_C.Interfaces.repositories.ReviewRepository;
import com.Vote1_C.Vote1_C.model.Review;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EmailConfigImpl emailConfig;

    public void saveReview(String review,boolean createdAlready) throws JSONException {
        Review reviewDTO = Review.readJson(review);
        reviewRepository.save(reviewDTO);
        JSONObject object = new JSONObject(review);
        if (!object.isNull("voteIdIfCreatedFromVote") && !createdAlready){
            emailConfig.sendSimpleMail("1201441@isep.ipp.pt","Your vote  will be automatic  created if your review is approved by a moderator ","Vote Status");
        }
    }

    public void deleteReview(String review){
        Review reviewDTO = Review.readJson(review);
        reviewRepository.delete(reviewDTO);
    }

    public void updateDataBaseReview(String review){
        try{
            JSONArray array = new JSONArray(review);

            for(int i = 0; i < array.length(); i++) {
                JSONObject jsonObject1 = array.getJSONObject(i);
                ObjectMapper objectMapper = new ObjectMapper();
                Review rv = objectMapper.readValue(jsonObject1.toString(), Review.class);
                System.out.println("RV: " + rv.getReviewId());
                reviewRepository.save(rv);
            }

        }catch(Exception e) {
            System.out.println("Error in Result as " + e.toString());
        }
    }

    public boolean checkStatusReview(String review){
        Review reviewDTO = Review.readJson(review);
        return reviewDTO.isApproved();
    }
}
