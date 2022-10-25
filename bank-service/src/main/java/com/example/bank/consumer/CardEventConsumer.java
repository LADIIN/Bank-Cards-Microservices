package com.example.bank.consumer;

import com.example.bank.service.CardService;
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
            containerFactory = "kafkaListenerContainerFactory")
    public void listenForCard(CardEvent cardEvent) {
        if (cardEvent.getStatus() == EventStatus.FAILED) {
            cardService.delete(cardEvent.getCardId());
        }
    }
}
