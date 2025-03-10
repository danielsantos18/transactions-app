package com.example.notification_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.notification_service.infrastructure.config.TwilioConfig;
import com.twilio.Twilio;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class NotificationServiceApplication {

	@Autowired
	private TwilioConfig twilioConfig;

	@PostConstruct
	public void initTwilio() {
		// System.out.println("Inicializando Twilio con SID: " +
		// twilioConfig.getAccountSid());
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
		// System.out.println("Twilio inicializado correctamente.");
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
