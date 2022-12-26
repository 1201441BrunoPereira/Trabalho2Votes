package com.Vote2_Q.Vote2_Q.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "temporaryVote")
public class TemporaryVote {

    @Column
    private boolean vote;

    @Column
    private Long userId;

    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;

    public static String generateUUID(){
        UUID Uuid = UUID.randomUUID();
        String id = Uuid.toString();
        return  id;
    }

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = generateUUID();
    }

    public TemporaryVote() {
    }

    public TemporaryVote(boolean vote, Long userId) {
        this.vote = vote;
        this.userId = userId;
        setId();
    }
}
