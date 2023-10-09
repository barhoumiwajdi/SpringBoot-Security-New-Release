package com.example.springbootjwtsecurity;

import com.example.springbootjwtsecurity.Auth.ResgisterRequest;
import com.example.springbootjwtsecurity.Model.Role;
import com.example.springbootjwtsecurity.Services.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class SpringBootJwtSecurityExplainedAuthorizationAuthenticationAndTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtSecurityExplainedAuthorizationAuthenticationAndTokenApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = ResgisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@gmail.com")
                    .password("password")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Admin Détails: " + service.register(admin));

            var manager = ResgisterRequest.builder()
                    .firstname("Manager")
                    .lastname("Manager")
                    .email("manager@gmail.com")
                    .password("password")
                    .role(Role.MANAGER)
                    .build();
            System.out.println("Manager Détails: " + service.register(manager));

            var user = ResgisterRequest.builder()
                    .firstname("user")
                    .lastname("user")
                    .email("user@gmail.com")
                    .password("password")
                    .role(Role.USER)
                    .build();
            System.out.println("User Détails: " + service.register(user));

        };
    }
}


