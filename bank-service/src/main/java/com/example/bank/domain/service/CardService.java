package com.example.bank.domain.service;

import com.example.bank.domain.entity.Card;
import com.example.bank.exception.NotFoundException;
import com.example.bank.properties.CardStatusProperties;
import com.example.bank.domain.repository.CardRepository;
import com.example.bank.domain.service.producer.CardEventProducer;
import lombok.RequiredArgsConstructor;
import org.example.message.CardEvent;
import org.example.message.EventStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CardStatusProperties cardStatusProperties;
    private final CardEventProducer cardProducer;

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
        Timestamp creationTime = Timestamp.valueOf(LocalDateTime.now());
        CardEvent cardEvent = new CardEvent(card.getId(), card.getAccount().getId(), creationTime, EventStatus.CREATED);
        cardProducer.produce(cardEvent);
        return savedCard;
    }

    @Transactional
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
