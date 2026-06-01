package com.tony.microservices;

import com.tony.security_module.core.reactive.ReactiveJwtConfig;
import com.tony.security_module.core.reactive.ReactiveSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = "com.tony")
public class MicroservicesApplication {

  public static void main(String[] args) {
    SpringApplication.run(MicroservicesApplication.class, args);
  }
}
