package com.Vote1_C.Vote1_C.Interfaces.repositories;

import com.Vote1_C.Vote1_C.model.TemporaryVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryVoteRepository extends JpaRepository<TemporaryVote, String> {
}
