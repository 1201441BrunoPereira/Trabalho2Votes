package com.Vote1_C.Vote1_C.repositories;



import com.Vote1_C.Vote1_C.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT v FROM Vote v WHERE v.reviewId = :reviewId")
    List<Vote> findId(Long reviewId);

    @Query("SELECT v FROM Vote v WHERE v.reviewId = :reviewId AND v.userId = :userId")
    Vote findReviewIdAndUserId(Long reviewId, Long userId);

    @Query("SELECT v FROM Vote v ")
    List<Vote> findAllVotes();

}
