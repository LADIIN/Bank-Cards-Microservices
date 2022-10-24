package com.example.bank.service;

import com.example.bank.entity.RefreshTokenCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@Testcontainers
@SpringBootTest
public class RefreshTokenCacheServiceContainerTest {

    private static final String TOKEN_VALUE = "tokenValue";
    private static final String IMAGE = "redis:alpine";
    private static final String ID = "refreshToken";
    private static final Integer PORT = 6379;

    @Autowired
    RefreshTokenCacheService refreshTokenCacheService;

    @Container
    public static final GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse(IMAGE))
            .withExposedPorts(PORT);

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", redisContainer::getHost);
        registry.add("spring.redis.port", redisContainer.getMappedPort(PORT)::toString);
    }

    @BeforeEach
    public void setUp() {
        redisContainer.start();
        RefreshTokenCache refreshTokenCache = new RefreshTokenCache(ID, TOKEN_VALUE);
        refreshTokenCacheService.save(refreshTokenCache);
    }

    @Test
    public void findById_WhenExists_ShouldReturnRefreshTokenCache() {
        RefreshTokenCache expected = new RefreshTokenCache(ID, TOKEN_VALUE);
        RefreshTokenCache actual = refreshTokenCacheService.findById(ID).orElse(null);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
}
