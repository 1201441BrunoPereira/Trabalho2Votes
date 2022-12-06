package com.Vote1_C.Vote1_C.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

    @Column
    private String reviewId;

    @Column
    private boolean isApproved;

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Review(String reviewId, boolean isApproved) {
        final Review rv = new Review();
        rv.setApproved(isApproved);
        rv.setReviewId(reviewId);
    }
}
