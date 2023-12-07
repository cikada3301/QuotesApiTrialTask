package com.example.qutesapi.service.impl;

import com.example.qutesapi.exception.QuoteNotFoundException;
import com.example.qutesapi.exception.VoteExistException;
import com.example.qutesapi.model.Quote;
import com.example.qutesapi.model.User;
import com.example.qutesapi.model.Vote;
import com.example.qutesapi.model.VoteHistory;
import com.example.qutesapi.model.enums.VoteType;
import com.example.qutesapi.repository.QuoteRepository;
import com.example.qutesapi.repository.UserRepository;
import com.example.qutesapi.repository.VoteHistoryRepository;
import com.example.qutesapi.repository.VoteRepository;
import com.example.qutesapi.security.userDetails.JwtUser;
import com.example.qutesapi.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final VoteHistoryRepository voteHistoryRepository;
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;

    @Override
    @Transactional
    public void create(Long quoteId, String voteType, JwtUser jwtUser) {

        User user = userRepository.findByEmail(jwtUser.getUsername());

        Quote quote = quoteRepository.findById(quoteId).orElseThrow(() -> new QuoteNotFoundException("This task doesn't exist"));

        VoteType type = VoteType.valueOf(voteType);

        Vote vote = voteRepository.findByQuoteAndVoter(quote, user)
                .map(v -> {
                    if(!v.getVoteType().equals(type)) {
                        v.setVoteType(type);
                        return v;
                    } else {
                        throw new VoteExistException("You already voted");
                    }
                })
                .orElseGet(() -> {
                    Vote newVote = new Vote();
                    newVote.setVoter(user);
                    newVote.setVoteType(type);
                    newVote.setQuote(quote);
                    return newVote;
                });

        voteRepository.save(vote);

        VoteHistory voteHistory = new VoteHistory();
        voteHistory.setVote(vote);
        voteHistory.setTimestamp(LocalDate.now());

        voteHistoryRepository.save(voteHistory);
    }

    @Override
    @Transactional
    public List<VoteHistory> getVoteHistoryOnGraphicByQuote(Long id) {

        List<Vote> votes = voteRepository.findByQuote(quoteRepository.findById(id).get());

        List<VoteHistory> histories = new ArrayList<>();

        for(Vote vote : votes) {
            histories.addAll(vote.getHistories());
        }

        return histories;
    }
}
