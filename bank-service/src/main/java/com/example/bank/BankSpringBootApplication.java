package com.example.bank;

import com.example.bank.properties.CardStatusProperties;
import com.example.bank.properties.JwtProperties;
import com.example.bank.properties.KafkaProperties;
import com.example.bank.properties.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CardStatusProperties.class, JwtProperties.class, RedisProperties.class, KafkaProperties.class})
public class BankSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankSpringBootApplication.class, args);
    }
}
