package com.example.bank.configuration.kafka;

import com.example.bank.properties.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaTopicConfiguration {

    private final KafkaProperties kafkaProperties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic bankToHistoryServiceTopic() {
        return TopicBuilder.name(kafkaProperties.getBankToHistoryTopicName())
                .partitions(kafkaProperties.getPartitionsAmount())
                .compact()
                .build();
    }

    @Bean
    public NewTopic historyToBankTopic() {
        return TopicBuilder.name(kafkaProperties.getHistoryToBankTopicName())
                .partitions(kafkaProperties.getPartitionsAmount())
                .compact()
                .build();
    }
}
