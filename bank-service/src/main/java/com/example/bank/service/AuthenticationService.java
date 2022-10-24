package com.example.bank.service;

import com.example.bank.controller.dto.JwtTokenDto;
import com.example.bank.entity.Employee;
import com.example.bank.exception.JwtAuthenticationException;
import com.example.bank.exception.NotFoundException;
import com.example.bank.repository.EmployeeRepository;
import com.example.bank.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtTokenDto authenticate(String username, String password) {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow(NotFoundException::new);
        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new JwtAuthenticationException("Invalid password.");
        }
        String accessToken = jwtTokenProvider.generateAccessToken(username);
        String refreshToken = jwtTokenProvider.generateRefreshToken(username);
        jwtTokenProvider.store(username, refreshToken);
        return new JwtTokenDto(accessToken, refreshToken);
    }

    public JwtTokenDto refresh(String refreshToken) throws JwtAuthenticationException {
        if (jwtTokenProvider.isTokenValid(refreshToken)) {
            String username = jwtTokenProvider.getUsername(refreshToken);
            String saveRefreshToken = jwtTokenProvider.getRefreshToken(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                Employee employee = employeeRepository.findByUsername(username).orElseThrow(NotFoundException::new);
                String accessToken = jwtTokenProvider.generateAccessToken(username);
                String newRefreshToken = jwtTokenProvider.generateRefreshToken(username);
                jwtTokenProvider.store(employee.getUsername(), newRefreshToken);
                return new JwtTokenDto(accessToken, newRefreshToken);
            }
        }
        throw new JwtAuthenticationException("Invalid refresh token.");
    }
}


