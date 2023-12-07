package com.example.qutesapi.service;

import com.example.qutesapi.dto.QuoteCreationDto;
import com.example.qutesapi.dto.QuoteViewDto;
import com.example.qutesapi.security.userDetails.JwtUser;

import java.util.List;

public interface QuoteService {

    List<QuoteViewDto> get();
    List<QuoteViewDto> getTopTen();
    List<QuoteViewDto> getWorseTen();
    QuoteViewDto getById(Long id);
    void create(QuoteCreationDto quoteCreationDto, JwtUser jwtUser);
    void update(Long id, QuoteCreationDto quoteCreationDto, JwtUser jwtUser);
    void remove(Long id, JwtUser jwtUser);
}
