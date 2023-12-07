package com.example.qutesapi.controller;

import com.example.qutesapi.dto.QuoteCreationDto;
import com.example.qutesapi.dto.QuoteViewDto;
import com.example.qutesapi.security.userDetails.JwtUser;
import com.example.qutesapi.service.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quote")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    public ResponseEntity<List<QuoteViewDto>> get() {

        return ResponseEntity.ok(quoteService.get());
    }

    @GetMapping("/top")
    public ResponseEntity<List<QuoteViewDto>> getTopTen() {

        return ResponseEntity.ok(quoteService.getTopTen());
    }

    @GetMapping("/worst")
    public ResponseEntity<List<QuoteViewDto>> getWorstTen() {

        return ResponseEntity.ok(quoteService.getWorseTen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuoteViewDto> getById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(quoteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody QuoteCreationDto quoteCreationDto, @AuthenticationPrincipal JwtUser jwtUser) {
        quoteService.create(quoteCreationDto, jwtUser);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody QuoteCreationDto quoteCreationDto, @AuthenticationPrincipal JwtUser jwtUser) {
        quoteService.update(id, quoteCreationDto, jwtUser);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long id, @AuthenticationPrincipal JwtUser jwtUser) {
        quoteService.remove(id, jwtUser);

        return ResponseEntity.ok().build();
    }
}
