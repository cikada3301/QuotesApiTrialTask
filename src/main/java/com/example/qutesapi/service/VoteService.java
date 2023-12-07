package com.example.qutesapi.service;

import com.example.qutesapi.model.VoteHistory;
import com.example.qutesapi.security.userDetails.JwtUser;

import java.util.List;

public interface VoteService {
    void create(Long quoteId, String voteType, JwtUser jwtUser);
    List<VoteHistory> getVoteHistoryOnGraphicByQuote(Long id);
}
