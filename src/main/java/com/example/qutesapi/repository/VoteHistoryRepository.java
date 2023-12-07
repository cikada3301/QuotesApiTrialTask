package com.example.qutesapi.repository;

import com.example.qutesapi.model.VoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteHistoryRepository extends JpaRepository<VoteHistory, Long> {
}
