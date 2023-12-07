package com.example.qutesapi.repository;

import com.example.qutesapi.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>, JpaSpecificationExecutor<Quote> {
}
