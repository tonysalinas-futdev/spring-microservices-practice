package com.tony.user_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientMicroserviceApplication {

	public static void main(String[] args){

        SpringApplication.run(ClientMicroserviceApplication.class, args);

	}

}
