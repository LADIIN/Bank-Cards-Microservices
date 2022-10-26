package com.example.bank.domain.repository;

import com.example.bank.domain.entity.RefreshTokenCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenCacheRepository extends CrudRepository<RefreshTokenCache, String> {
}
