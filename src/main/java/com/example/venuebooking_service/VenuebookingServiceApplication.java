package com.example.venuebooking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VenuebookingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VenuebookingServiceApplication.class, args);
    }
}