package com.example.bank.security;

import com.example.bank.domain.entity.RefreshTokenCache;
import com.example.bank.exception.JwtAuthenticationException;
import com.example.bank.exception.NotFoundException;
import com.example.bank.properties.JwtProperties;
import com.example.bank.domain.service.RefreshTokenCacheService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;


@Component
public class JwtTokenProvider {

    private static final String JWT_HEADER = "Authorization";
    private static final String JWT_BEARER = "Bearer_";

    private final Key secretKey;
    private final String signatureAlgorithm;
    private final Duration accessExpirationTimeMs;
    private final Duration refreshExpirationDateInMs;
    private final RefreshTokenCacheService refreshTokenCacheService;

    @Autowired
    public JwtTokenProvider(JwtProperties jwtProperties, RefreshTokenCacheService refreshTokenCacheService) {
        this.refreshTokenCacheService = refreshTokenCacheService;
        signatureAlgorithm = jwtProperties.getSignatureAlgorithm();
        accessExpirationTimeMs = jwtProperties.getAccessExpirationTimeMs();
        refreshExpirationDateInMs = jwtProperties.getRefreshExpirationTimeMs();
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.valueOf(signatureAlgorithm));
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpirationTimeMs.toMillis()))
                .signWith(secretKey, SignatureAlgorithm.forName(signatureAlgorithm)).compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationDateInMs.toMillis()))
                .signWith(secretKey, SignatureAlgorithm.forName(signatureAlgorithm)).compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> jwt = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
        return true;
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWT_HEADER);
        if (bearerToken != null && bearerToken.startsWith(JWT_BEARER)) {
            return bearerToken.substring(JWT_BEARER.length());
        }
        return null;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = new User(getUsername(token), "", new HashSet<>());
        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }

    public void store(String username, String refreshToken) {
        refreshTokenCacheService.save(new RefreshTokenCache(username, refreshToken));
    }

    public String getRefreshToken(String username) {
        RefreshTokenCache refreshTokenCache = refreshTokenCacheService.findById(username).orElseThrow(NotFoundException::new);
        return refreshTokenCache.getValue();
    }
}
