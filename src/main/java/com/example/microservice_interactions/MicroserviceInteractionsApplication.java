package com.example.microservice_interactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceInteractionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceInteractionsApplication.class, args);
	}

}
