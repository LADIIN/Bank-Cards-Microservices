package com.example.history.repository;

import com.example.history.entity.CardHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardHistoryRepository extends CrudRepository<CardHistory, Long> {
}
