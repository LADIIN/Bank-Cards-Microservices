package com.example.bank.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
@ConfigurationPropertiesScan
public class JwtProperties {

    String signatureAlgorithm;
    Duration accessExpirationTimeMs;
    Duration refreshExpirationTimeMs;

}
