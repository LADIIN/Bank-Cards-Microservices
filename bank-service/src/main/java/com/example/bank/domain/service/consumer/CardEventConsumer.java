package com.example.bank.domain.service.consumer;

import com.example.bank.domain.service.CardService;
import lombok.RequiredArgsConstructor;
import org.example.message.CardEvent;
import org.example.message.EventStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardEventConsumer {

    private final CardService cardService;

    @KafkaListener(topics = "${spring.kafka.history-to-bank-topic-name}",
            containerFactory = "cardEventListenerContainerFactory")
    public void consume(CardEvent cardEvent) {
        if (cardEvent.getStatus() == EventStatus.FAILED) {
            cardService.delete(cardEvent.getCardId());
        }
    }
}
