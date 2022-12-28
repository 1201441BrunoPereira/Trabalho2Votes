package com.RecoveryVoteQ.RecoveryVoteQ.repositories;

import com.RecoveryVoteQ.RecoveryVoteQ.model.TemporaryVote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporaryVoteRepository extends JpaRepository<TemporaryVote, String> {

    @Query("SELECT v FROM TemporaryVote v WHERE v.id = :voteId")
    TemporaryVote getTemporaryVoteById(String voteId);

    @Query("SELECT r FROM TemporaryVote r")
    List<TemporaryVote> getAllByPage(Pageable pageable);

}
