package com.example.qutesapi.listener;

import com.example.qutesapi.model.User;
import jakarta.persistence.PrePersist;

import java.time.LocalDate;

public class UserActionListener {

    @PrePersist
    private void beforeCreate(User user) {
        user.setDateCreated(LocalDate.now());
    }
}
