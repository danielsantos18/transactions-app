package com.example.transaction_service.infrastructure.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean userExists(String username) {
        String url = "http://USER-SERVICE/users/" + username + "/exists";
        return Boolean.TRUE.equals(restTemplate.getForObject(url, Boolean.class));
    }

    public BigDecimal getUserBalance(String username) {
        String url = "http://USER-SERVICE/users/" + username + "/balance";
        return restTemplate.getForObject(url, BigDecimal.class);
    }

    public void updateUserBalance(String username, BigDecimal newBalance) {
        String url = "http://USER-SERVICE/users/" + username + "/balance";
        restTemplate.put(url, newBalance);
    }
}