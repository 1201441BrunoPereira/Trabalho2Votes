package com.Vote2_C.Vote2_C.Interfaces.repositories;

import com.Vote2_C.Vote2_C.model.ReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewDTO, String> {

    @Query("SELECT r.isApproved FROM ReviewDTO r WHERE r.reviewId = :reviewId")
    Optional<Boolean> isVoted(@Param("reviewId") String reviewId);
}