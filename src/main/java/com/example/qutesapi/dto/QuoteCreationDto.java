package com.example.qutesapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuoteCreationDto {

    @NotBlank(message = "Content must not be blank")
    private String content;
}
