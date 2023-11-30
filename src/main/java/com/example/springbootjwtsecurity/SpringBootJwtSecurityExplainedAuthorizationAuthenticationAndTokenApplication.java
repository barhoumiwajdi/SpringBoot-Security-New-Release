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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

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
                    .city("Mhamdia")
                    .country("ben arous")
                    .postalCode("1145")
                    .state("tunisia")
                    .build();
            System.out.println("Admin Détails: " + service.register(admin));

            var manager = ResgisterRequest.builder()
                    .firstname("Manager")
                    .lastname("Manager")
                    .email("manager@gmail.com")
                    .password("password")
                    .role(Role.MANAGER)
                    .city("Mhamdia")
                    .country("ben arous")
                    .postalCode("1145")
                    .state("tunisia")
                    .build();
            System.out.println("Manager Détails: " + service.register(manager));

            var user = ResgisterRequest.builder()
                    .firstname("user")
                    .lastname("user")
                    .email("user@gmail.com")
                    .password("password")
                    .role(Role.USER)
                    .city("Mhamdia")
                    .country("ben arous")
                    .postalCode("1145")
                    .state("tunisia")
                    .build();
            System.out.println("User Détails: " + service.register(user));

        };
    }

/*
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
       // corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "File-Name"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }*/

}


