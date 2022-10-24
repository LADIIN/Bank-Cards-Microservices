package com.example.bank.service;

import com.example.bank.entity.RefreshTokenCache;
import com.example.bank.repository.RefreshTokenCacheRepository;
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

