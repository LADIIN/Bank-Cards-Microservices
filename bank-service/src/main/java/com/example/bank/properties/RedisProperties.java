package com.example.bank.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.redis")
@ConfigurationPropertiesScan
public class RedisProperties {

    String host;
    Integer port;
    String password;
}
