package com.tony.api_gateway;


import com.tony.security_module.core.JwtProperties;

import com.tony.security_module.core.reactive.ReactiveJwtConfig;
import com.tony.security_module.core.standard.JwtConfig;
import com.tony.security_module.core.standard.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.tony")
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
