package com.example.bank.domain.service;

import com.example.bank.domain.entity.RefreshTokenCache;
import com.example.bank.domain.repository.RefreshTokenCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenCacheService {

    private final RefreshTokenCacheRepository refreshTokenCacheRepository;

    public Optional<RefreshTokenCache> findById(String id) {
        return refreshTokenCacheRepository.findById(id);
    }

    public void save(RefreshTokenCache refreshTokenCache) {
        refreshTokenCacheRepository.save(refreshTokenCache);
    }
}

