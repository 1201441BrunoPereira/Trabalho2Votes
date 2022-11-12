package com.Vote1_C.Vote1_C.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "vote")
public class Vote {

    @Column
    private UUID uuidReview;


    @Column
    private boolean vote;

    @Column
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    public Vote() {
    }

    public UUID getUuidReview() {
        return uuidReview;
    }

    public void setUuidReview(UUID uuidReview) {
        this.uuidReview = uuidReview;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Vote(UUID uuidReview, boolean vote) {
        this.uuidReview = uuidReview;
        this.vote = vote;
    }

}
