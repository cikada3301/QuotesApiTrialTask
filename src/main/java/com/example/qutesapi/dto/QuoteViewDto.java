package com.example.qutesapi.dto;

import com.example.qutesapi.model.User;
import com.example.qutesapi.model.Vote;
import lombok.Data;

import java.util.List;

@Data
public class QuoteViewDto {
    private String content;

    private User author;

    private List<Vote> votes;
}
