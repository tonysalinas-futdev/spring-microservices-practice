package com.tony.user_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientMicroserviceApplication {

	public static void main(String[] args) {
        System.out.println("JDBC URL env: " + System.getenv("DATABASE_URL"));
        System.out.println("USER: " + System.getenv("DATABASE_USER"));

        System.out.println("spring.datasource.url: " + System.getProperty("spring.datasource.url"));
        System.out.println("spring.datasource.u: " + System.getProperty("spring.datasource.user"));

        SpringApplication.run(ClientMicroserviceApplication.class, args);

	}

}
