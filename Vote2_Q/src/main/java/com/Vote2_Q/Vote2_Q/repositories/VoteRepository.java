package com.Vote2_Q.Vote2_Q.repositories;

import com.Vote2_Q.Vote2_Q.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {

    @Query("SELECT v FROM Vote v WHERE v.reviewId = :reviewId")
    List<Vote> findId(String reviewId);

    @Query("SELECT v FROM Vote v WHERE v.reviewId = :reviewId AND v.userId = :userId")
    Vote findReviewIdAndUserId(String reviewId, Long userId);

    @Query("SELECT v FROM Vote v ")
    List<Vote> findAllVotes();

    @Query("SELECT v FROM Vote v WHERE v.id = :voteId")
    Vote findVoteById(String voteId);
}
