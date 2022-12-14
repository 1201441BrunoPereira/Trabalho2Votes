package com.RecoveryVoteC.RecoveryVoteC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    public static String generateUUID(){
        UUID Uuid = UUID.randomUUID();
        String id = Uuid.toString();
        return  id;
    }

    public Vote(String reviewId, boolean vote) {
        this.reviewId = reviewId;
        this.vote = vote;
    }

}
