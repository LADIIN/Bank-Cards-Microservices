package com.example.bank.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("refreshToken")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class RefreshTokenCache implements Serializable {

    String id;
    String value;
}


