package com.example.history.consumer;

import lombok.RequiredArgsConstructor;
import org.example.message.CardMessage;
import org.springframework.kafka.annotation.KafkaListener;


@RequiredArgsConstructor
public class CardConsumer {

    @KafkaListener(topics = "${spring.kafka.topic-name}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenForCard(CardMessage cardMessage) {

    }
}
