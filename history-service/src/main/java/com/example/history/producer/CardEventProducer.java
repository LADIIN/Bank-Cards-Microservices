package com.example.history.producer;

import com.example.history.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.example.message.CardEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardEventProducer {

    private final KafkaTemplate<String, CardEvent> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void send(CardEvent cardEvent) {
        kafkaTemplate.send(kafkaProperties.getBankToHistoryTopicName(), cardEvent);
    }
}
