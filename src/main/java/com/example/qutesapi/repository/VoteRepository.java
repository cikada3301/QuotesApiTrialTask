package com.example.qutesapi.repository;

import com.example.qutesapi.model.Quote;
import com.example.qutesapi.model.User;
import com.example.qutesapi.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByQuote(Quote quote);
    Optional<Vote> findByQuoteAndVoter(Quote quote, User user);
}
