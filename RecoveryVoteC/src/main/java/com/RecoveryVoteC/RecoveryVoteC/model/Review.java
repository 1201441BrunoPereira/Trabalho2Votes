package com.RecoveryVoteC.RecoveryVoteC.model;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String reviewId;

    @Column
    private boolean isApproved;

    public Review() {

    }

    public Review(String reviewId, boolean isApproved) {
        this.reviewId = reviewId;
        this.isApproved = isApproved;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public static Review readJson(String json){
        Review review = new Review();
        try{

            JSONObject object = new JSONObject(json);
            review.setApproved(Objects.equals(object.getString("status"), "APPROVED"));
            review.setReviewId(object.getString("reviewId"));

        }catch(Exception e) {
            System.out.println("Error in Result as " + e);
        }

        return review;
    }

}
