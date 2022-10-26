package com.example.history.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.kafka")
@ConfigurationPropertiesScan
public class KafkaProperties {

    String bootstrapServers;
    String bankToHistoryTopicName;
    String historyToBankTopicName;
    Integer partitionsAmount;
    String groupId;
}
