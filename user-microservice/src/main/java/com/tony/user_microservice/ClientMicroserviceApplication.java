package com.tony.user_microservice;

import com.tony.user_microservice.dtos.AdminCreateUserDTO;
import com.tony.user_microservice.enums.Role;
import com.tony.user_microservice.repository.RoleRepository;
import com.tony.user_microservice.repository.UserRepository;
import com.tony.user_microservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
public class ClientMicroserviceApplication {

	public static void main(String[] args){

        SpringApplication.run(ClientMicroserviceApplication.class, args);

	}

}
