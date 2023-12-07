package com.example.qutesapi.listener;

import com.example.qutesapi.model.Quote;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDate;

public class QuoteActionListener {

    @PrePersist
    private void beforeCreate(Quote quote) {
        quote.setDateCreated(LocalDate.now());
    }

    @PreUpdate
    private void beforeUpdate(Quote quote) {
        quote.setDateUpdated(LocalDate.now());
    }
}
