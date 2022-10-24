package com.example.bank.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.HashMap;

@Getter
@Setter
@ConfigurationProperties(prefix = "logical-card-status")
@ConfigurationPropertiesScan
public class CardStatusProperties {

    HashMap<String, String> statuses;
    String undefined;
}
