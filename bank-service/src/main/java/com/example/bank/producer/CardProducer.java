package com.example.bank.producer;

import com.example.bank.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.example.message.CardMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardProducer {

    private final KafkaTemplate<String, CardMessage> kafkaTemplate;
    private final KafkaProperties kafkaProperties;

    public void send(CardMessage cardMessage) {
        kafkaTemplate.send(kafkaProperties.getTopicName(), cardMessage);
    }
}
