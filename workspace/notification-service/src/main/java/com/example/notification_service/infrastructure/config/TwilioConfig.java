package com.example.notification_service.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String trialNumber;
}
