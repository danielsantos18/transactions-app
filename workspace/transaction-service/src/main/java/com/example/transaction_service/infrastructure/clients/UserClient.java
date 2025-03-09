package com.example.transaction_service.infrastructure.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.UUID;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Obtener saldo del usuario
    public BigDecimal getUserBalance(UUID userId) {
        String url = "http://USER-SERVICE/users/" + userId + "/balance";
        return restTemplate.getForObject(url, BigDecimal.class);
    }

    // Validar si el usuario existe
    public boolean userExists(UUID userId) {
        String url = "http://USER-SERVICE/users/" + userId + "/exists";
        return Boolean.TRUE.equals(restTemplate.getForObject(url, Boolean.class));
    }

    // Actualizar saldo del usuario
    public void updateUserBalance(UUID userId, BigDecimal newBalance) {
        String url = "http://USER-SERVICE/users/" + userId + "/balance";
        restTemplate.put(url, newBalance);
    }
}


