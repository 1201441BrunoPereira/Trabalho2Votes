package com.RecoveryVoteC.RecoveryVoteC.repositories;

import com.RecoveryVoteC.RecoveryVoteC.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.reviewId = :reviewId AND v.userId = :userId")
    Vote findReviewIdAndUserId(String reviewId, Long userId);


}
