package com.example.history.consumer;

import com.example.history.entity.CardHistory;
import com.example.history.producer.CardEventProducer;
import com.example.history.service.CardHistoryService;
import lombok.RequiredArgsConstructor;
import org.example.message.CardEvent;
import org.example.message.EventStatus;
import org.springframework.kafka.annotation.KafkaListener;


@RequiredArgsConstructor
public class CardEventConsumer {

    private final CardHistoryService cardHistoryService;
    private final CardEventProducer cardEventProducer;

    @KafkaListener(topics = "${spring.kafka.bank-to-history-topic-name}",
            containerFactory = "kafkaListenerContainerFactory", groupId = "bank-service")
    public void listenForCard(CardEvent cardEvent) {
        CardHistory cardHistory = new CardHistory();
        cardHistory.setCardId(cardEvent.getCardId());
        cardHistory.setAccountId(cardEvent.getAccountId());
        cardHistory.setCreationTime(cardEvent.getCreationTime());
        try {
            cardHistoryService.save(cardHistory);
        } catch (RuntimeException e) {
            cardEvent.setStatus(EventStatus.FAILED);
            cardEventProducer.send(cardEvent);
        }
    }
}
