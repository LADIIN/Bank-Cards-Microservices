package com.example.history.domain.repository;

import com.example.history.domain.entity.CardHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardHistoryRepository extends CrudRepository<CardHistory, Long> {
}
