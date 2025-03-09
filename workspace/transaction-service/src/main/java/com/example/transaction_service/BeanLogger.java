package com.example.transaction_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class BeanLogger implements CommandLineRunner {

    private final ApplicationContext ctx;

    public BeanLogger(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run(String... args) {
        System.out.println("===== BEANS REGISTRADOS EN SPRING =====");
        Arrays.stream(ctx.getBeanDefinitionNames())
              .sorted()
              .forEach(System.out::println);
    }
}
