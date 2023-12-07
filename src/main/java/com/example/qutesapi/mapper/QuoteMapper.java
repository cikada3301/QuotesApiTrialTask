package com.example.qutesapi.mapper;

import com.example.qutesapi.dto.QuoteCreationDto;
import com.example.qutesapi.dto.QuoteViewDto;
import com.example.qutesapi.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QuoteMapper {
    QuoteMapper INSTANCE = Mappers.getMapper(QuoteMapper.class);

    Quote quoteCreationDtoToQuote(QuoteCreationDto quoteCreationDto);
    QuoteViewDto quoteToQuoteViewDto(Quote quote);
}
