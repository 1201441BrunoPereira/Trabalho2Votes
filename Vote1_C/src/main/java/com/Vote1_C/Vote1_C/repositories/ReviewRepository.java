package com.Vote1_C.Vote1_C.repositories;

import com.Vote1_C.Vote1_C.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r.isApproved FROM Review r WHERE r.reviewId = :reviewId")
    Optional<Boolean> isVoted(@Param("reviewId") String reviewId);

    @Query("SELECT r FROM Review r WHERE r.reviewId = :reviewId")
    Review findByReviewId(@Param("reviewId") String reviewId);
}
