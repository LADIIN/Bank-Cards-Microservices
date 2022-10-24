package com.example.bank.controller;

import com.example.bank.controller.dto.JwtRefreshTokenDto;
import com.example.bank.controller.dto.JwtTokenDto;
import com.example.bank.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/auth")
    public JwtTokenDto authenticate(@RequestParam String username, @RequestParam String password) {
        return authenticationService.authenticate(username, password);
    }

    @PostMapping("/refresh")
    public JwtTokenDto refresh(@RequestBody JwtRefreshTokenDto jwtRefreshTokenDto) {
        return authenticationService.refresh(jwtRefreshTokenDto.getRefreshToken());
    }
}
