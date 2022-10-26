package com.example.history.domain.service.consumer;

import com.example.history.domain.entity.CardHistory;
import com.example.history.domain.service.CardHistoryService;
import com.example.history.domain.service.producer.CardEventProducer;
import com.example.history.domain.mapper.CardHistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.message.CardEvent;
import org.example.message.EventStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardEventConsumer {

    private final CardHistoryService cardHistoryService;
    private final CardEventProducer cardEventProducer;
    private final CardHistoryMapper cardHistoryMapper;

    @KafkaListener(
            topics = "${spring.kafka.bank-to-history-topic-name}",
            containerFactory = "cardEventListenerContainerFactory",
            groupId = "${spring.kafka.group-id}")
    public void consumeCardEvent(CardEvent cardEvent) {
        CardHistory cardHistory = cardHistoryMapper.mapEventToObject(cardEvent);
        try {
            cardHistoryService.save(cardHistory);
        } catch (RuntimeException e) {
            cardEvent.setStatus(EventStatus.FAILED);
            cardEventProducer.produce(cardEvent);
        }
    }
}
