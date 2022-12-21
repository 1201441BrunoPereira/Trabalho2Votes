package com.Vote1_C.Vote1_C.Interfaces.repositories;

import com.Vote1_C.Vote1_C.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {

    @Query("SELECT v FROM Vote v WHERE v.reviewId = :reviewId AND v.userId = :userId")
    Vote findReviewIdAndUserId(String reviewId, Long userId);

}
