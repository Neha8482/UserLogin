package com.example.Userlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.example.Userlogin")
@EnableJpaRepositories("com.example.Userlogin")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, scanBasePackages= {"com.example.UserLogin.applicationn"})
public class UserloginApplication {
	public static void main(String[] args) {
		System.out.println("Its started");
		SpringApplication.run(UserloginApplication.class, args);
	}
}
