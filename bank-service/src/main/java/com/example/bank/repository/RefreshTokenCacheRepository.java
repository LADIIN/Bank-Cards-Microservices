package com.example.bank.repository;

import com.example.bank.entity.RefreshTokenCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenCacheRepository extends CrudRepository<RefreshTokenCache, String> {
}
