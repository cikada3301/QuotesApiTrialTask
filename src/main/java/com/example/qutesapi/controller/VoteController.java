package com.example.qutesapi.controller;

import com.example.qutesapi.model.VoteHistory;
import com.example.qutesapi.security.userDetails.JwtUser;
import com.example.qutesapi.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @GetMapping("/{quoteId}")
    public ResponseEntity<List<VoteHistory>> getVoteHistoryOnGraphicByQuote(@PathVariable Long quoteId) {
        return ResponseEntity.ok(voteService.getVoteHistoryOnGraphicByQuote(quoteId));
    }

    @PostMapping("/{quoteId}")
    public ResponseEntity<Void> create(@PathVariable Long quoteId, @Valid @RequestParam("voteType") String voteType, @AuthenticationPrincipal JwtUser jwtUser) {
        voteService.create(quoteId, voteType, jwtUser);

        return ResponseEntity.ok().build();
    }
}
