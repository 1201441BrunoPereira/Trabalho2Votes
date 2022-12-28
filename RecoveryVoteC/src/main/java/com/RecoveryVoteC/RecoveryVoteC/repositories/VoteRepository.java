package com.RecoveryVoteC.RecoveryVoteC.repositories;

import com.RecoveryVoteC.RecoveryVoteC.model.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("SELECT f FROM Vote f")
    List<Vote> getAllByPage(Pageable pageable);

}
