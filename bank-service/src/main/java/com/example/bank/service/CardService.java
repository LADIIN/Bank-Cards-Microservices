package com.example.bank.service;

import com.example.bank.entity.Card;
import com.example.bank.exception.NotFoundException;
import com.example.bank.producer.CardProducer;
import com.example.bank.properties.CardStatusProperties;
import com.example.bank.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardStatusProperties cardStatusProperties;
    private final CardProducer cardProducer;

    public Card findById(Long id) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        return cardOptional.orElseThrow(NotFoundException::new);
    }

    public String getLogicCardStatus(Card card) {
        String status = card.getStatus();
        String undefinedStatus = cardStatusProperties.getUndefined();
        return cardStatusProperties.getStatuses().getOrDefault(status, undefinedStatus);
    }

    @Transactional
    public Card save(Card card) {
        Card savedCard = cardRepository.save(card);
        return savedCard;
    }
}
