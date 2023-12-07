package com.example.qutesapi.controller;

import com.example.qutesapi.dto.UserAuthenticationDto;
import com.example.qutesapi.dto.UserRegistrationDto;
import com.example.qutesapi.security.JwtUtil;
import com.example.qutesapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/authentication")
    public ResponseEntity<String> authenticate(@Valid @RequestBody UserAuthenticationDto userAuthenticationDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthenticationDto.getEmail(), userAuthenticationDto.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userAuthenticationDto.getEmail());

        if (userDetails == null) {
            throw new BadCredentialsException("Incorrect email or password");
        }

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
    }

    @PostMapping("/registration")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        userService.register(userRegistrationDto);

        return ResponseEntity.ok().build();
    }
}
