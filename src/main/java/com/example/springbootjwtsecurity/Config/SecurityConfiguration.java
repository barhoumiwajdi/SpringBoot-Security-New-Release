package com.example.springbootjwtsecurity.Config;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import java.time.LocalDateTime;

import static com.example.springbootjwtsecurity.Model.Permission.ADMIN_CREATE;
import static com.example.springbootjwtsecurity.Model.Permission.ADMIN_READ;
import static com.example.springbootjwtsecurity.Model.Permission.ADMIN_UPDATE;
import static com.example.springbootjwtsecurity.Model.Permission.ADMIN_DELETE;
import static com.example.springbootjwtsecurity.Model.Permission.MANAGER_READ;
import static com.example.springbootjwtsecurity.Model.Permission.MANAGER_UPDATE;
import static com.example.springbootjwtsecurity.Model.Permission.MANAGER_DELETE;
import static com.example.springbootjwtsecurity.Model.Permission.MANAGER_CREATE;

import static com.example.springbootjwtsecurity.Model.Role.ADMIN;
import static com.example.springbootjwtsecurity.Model.Role.USER;
import static com.example.springbootjwtsecurity.Model.Role.MANAGER;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
   private final  JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**"} ;
    private final LogoutHandler logoutHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http    .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(WHITE_LIST_URL)
                .permitAll()
                .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                .requestMatchers("/api/v1/admin/**").hasAnyRole(ADMIN.name())
                .requestMatchers("/api/v1/user/**").hasAnyRole(USER.name() , ADMIN.name())
                .requestMatchers("/api/v1/management/**").hasAnyAuthority( MANAGER_READ.name() , ADMIN_READ.name())
                .requestMatchers("/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                .requestMatchers("/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                .requestMatchers("/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;




        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) ->
                {
                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write(new JSONObject()
                            .put("timestamp", LocalDateTime.now())
                            .put("message", "Access denied")
                            .put("Code" , 403)
                            .put("Important" , " Try To Verify Your Account Access")

                            .toString());
                });
        return http.build();
    }


}
