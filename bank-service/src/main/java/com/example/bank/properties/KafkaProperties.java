package com.example.bank.properties;

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
    String topicName;
    Integer partitionAmount;
    Short partitionFactor;
}
