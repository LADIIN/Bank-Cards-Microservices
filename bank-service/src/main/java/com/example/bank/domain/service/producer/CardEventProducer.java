package com.example.bank.domain.service.producer;

import com.example.bank.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.message.CardEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardEventProducer {

    private final KafkaTemplate<String, CardEvent> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void produce(CardEvent cardEvent) {
        kafkaTemplate.send(kafkaProperties.getBankToHistoryTopicName(), "card-event", cardEvent);
    }
}
