package com.Vote1_C.Vote1_C.model;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Column
    private String reviewId;

    @Column
    private boolean isApproved;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Review() {

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


    public static Review newReview(String reviewId, boolean isApproved) {
        final Review rv = new Review();
        rv.setApproved(isApproved);
        rv.setReviewId(reviewId);
        return rv;
    }
}
