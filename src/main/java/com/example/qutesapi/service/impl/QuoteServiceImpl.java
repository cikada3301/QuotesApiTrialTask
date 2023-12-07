package com.example.qutesapi.service.impl;

import com.example.qutesapi.dto.QuoteCreationDto;
import com.example.qutesapi.dto.QuoteViewDto;
import com.example.qutesapi.exception.NotQuoteOwnerException;
import com.example.qutesapi.exception.QuoteNotFoundException;
import com.example.qutesapi.mapper.QuoteMapper;
import com.example.qutesapi.model.Quote;
import com.example.qutesapi.model.Vote;
import com.example.qutesapi.model.User;
import com.example.qutesapi.model.VoteHistory;
import com.example.qutesapi.model.enums.VoteType;
import com.example.qutesapi.repository.QuoteRepository;
import com.example.qutesapi.repository.UserRepository;
import com.example.qutesapi.repository.VoteHistoryRepository;
import com.example.qutesapi.repository.VoteRepository;
import com.example.qutesapi.security.userDetails.JwtUser;
import com.example.qutesapi.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final QuoteMapper quoteMapper = QuoteMapper.INSTANCE;

    @Override
    @Transactional
    public List<QuoteViewDto> get() {

        return quoteRepository.findAll()
                .stream()
                .map(quoteMapper::quoteToQuoteViewDto)
                .toList();
    }

    @Override
    @Transactional
    public List<QuoteViewDto> getTopTen() {

        List<Vote> votes = voteRepository.findAll();

        Map<Quote, Map<VoteType, Long>> counts = votes.stream()
                .collect(Collectors.groupingBy(Vote::getQuote,
                        Collectors.groupingBy(Vote::getVoteType, Collectors.counting())));

        Comparator<Map.Entry<Quote, Map<VoteType, Long>>> comparator =
                Comparator.comparing(
                        entry -> entry.getValue().get(VoteType.UPVOTE),
                        Comparator.nullsLast(Comparator.reverseOrder())
                );

        return counts.entrySet().stream()
                .sorted(comparator)
                .map(Map.Entry::getKey)
                .limit(10)
                .map(quoteMapper::quoteToQuoteViewDto)
                .toList();
    }

    @Override
    @Transactional
    public List<QuoteViewDto> getWorseTen() {
        List<Vote> votes = voteRepository.findAll();

        Map<Quote, Map<VoteType, Long>> counts = votes.stream()
                .collect(Collectors.groupingBy(Vote::getQuote,
                        Collectors.groupingBy(Vote::getVoteType, Collectors.counting())));

        Comparator<Map.Entry<Quote, Map<VoteType, Long>>> comparator =
                Comparator.comparing(
                        entry -> entry.getValue().get(VoteType.DOWNVOTE),
                        Comparator.nullsLast(Comparator.reverseOrder())
                );

        return counts.entrySet().stream()
                .sorted(comparator)
                .map(Map.Entry::getKey)
                .limit(10)
                .map(quoteMapper::quoteToQuoteViewDto)
                .toList();
    }

    @Override
    @Transactional
    public QuoteViewDto getById(Long id) {

        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException("This quote doesn't exist"));

        QuoteViewDto quoteViewDto = quoteMapper.quoteToQuoteViewDto(quote);

        return quoteViewDto;
    }

    @Override
    @Transactional
    public void create(QuoteCreationDto quoteCreationDto, JwtUser jwtUser) {

        User user = userRepository.findByEmail(jwtUser.getUsername());

        Quote quote = quoteMapper.quoteCreationDtoToQuote(quoteCreationDto);
        quote.setAuthor(user);

        quoteRepository.save(quote);
    }

    @Override
    @Transactional
    public void update(Long id, QuoteCreationDto quoteCreationDto, JwtUser jwtUser) {

        Quote quoteOnUpdate = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException("This quote doesn't exist"));

        User user = userRepository.findByEmail(jwtUser.getUsername());

        if (!Objects.equals(quoteOnUpdate.getAuthor().getId(), user.getId())) {
            throw new NotQuoteOwnerException("You are not author");
        }

        quoteOnUpdate = quoteMapper.quoteCreationDtoToQuote(quoteCreationDto);
        quoteOnUpdate.setId(id);

        quoteRepository.save(quoteOnUpdate);
    }

    @Override
    @Transactional
    public void remove(Long id, JwtUser jwtUser) {

        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException("This quote doesn't exist"));

        User user = userRepository.findByEmail(jwtUser.getUsername());

        if (!Objects.equals(quote.getAuthor().getId(), user.getId())) {
            throw new NotQuoteOwnerException("You are not author");
        }

        quoteRepository.deleteById(id);
    }
}
