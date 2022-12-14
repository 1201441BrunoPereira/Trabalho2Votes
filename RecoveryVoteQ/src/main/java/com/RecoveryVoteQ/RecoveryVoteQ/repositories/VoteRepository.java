package com.RecoveryVoteQ.RecoveryVoteQ.repositories;

import com.RecoveryVoteQ.RecoveryVoteQ.model.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {

    @Query("SELECT r FROM Vote r")
    List<Vote> getAllByPage(Pageable pageable);

}
