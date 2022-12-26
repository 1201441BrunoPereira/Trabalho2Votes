package com.Vote2_Q.Vote2_Q.Interefaces.repositories;


import com.Vote2_Q.Vote2_Q.model.TemporaryVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemporaryVoteRepository extends JpaRepository<TemporaryVote, String> {

    @Query("SELECT v FROM TemporaryVote v WHERE v.id = :voteId")
    TemporaryVote getTemporaryVoteById(String voteId);

    @Query("SELECT v FROM TemporaryVote v ")
    List<TemporaryVote> getAll();

}
