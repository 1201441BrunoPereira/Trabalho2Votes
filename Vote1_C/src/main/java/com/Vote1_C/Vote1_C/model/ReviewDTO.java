package com.Vote1_C.Vote1_C.model;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "review")
public class ReviewDTO {

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String reviewId;

    @Column
    private boolean isApproved;

    public ReviewDTO() {

    }

    public ReviewDTO(String reviewId, boolean isApproved) {
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

    public static ReviewDTO readJson(String json){
        ReviewDTO reviewDTO = new ReviewDTO();
        try{

            JSONObject object = new JSONObject(json);
            reviewDTO.setApproved(Objects.equals(object.getString("status"), "APPROVED"));
            reviewDTO.setReviewId(object.getString("reviewId"));

        }catch(Exception e) {
            System.out.println("Error in Result as " + e);
        }

        return reviewDTO;
    }

}
