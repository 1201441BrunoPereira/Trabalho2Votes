package com.Vote1_Q.Vote1_Q.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "vote")
public class Vote {

    @Column
    private String reviewId;


    @Column
    private boolean vote;

    @Column
    private Long userId;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    public Vote() {
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String uuidReview) {
        this.reviewId = uuidReview;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Vote(String reviewId, boolean vote) {
        this.reviewId = reviewId;
        this.vote = vote;
    }

}
