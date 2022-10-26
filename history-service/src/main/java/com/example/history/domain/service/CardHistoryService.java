package com.example.history.domain.service;

import com.example.history.domain.entity.CardHistory;
import com.example.history.domain.repository.CardHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CardHistoryService {

    private final CardHistoryRepository cardHistoryRepository;

    @Transactional
    public void save(CardHistory cardHistory) {
        cardHistoryRepository.save(cardHistory);
    }
}
