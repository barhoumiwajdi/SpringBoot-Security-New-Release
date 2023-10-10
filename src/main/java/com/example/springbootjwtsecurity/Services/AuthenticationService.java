package com.example.springbootjwtsecurity.Services;


import com.example.springbootjwtsecurity.Auth.AuthenticationRequest;
import com.example.springbootjwtsecurity.Auth.AuthenticationResponse;
import com.example.springbootjwtsecurity.Auth.ResgisterRequest;
import com.example.springbootjwtsecurity.Config.JwtService;

import com.example.springbootjwtsecurity.Model.Role;
import com.example.springbootjwtsecurity.Model.User;
import com.example.springbootjwtsecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class AuthenticationService {
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder ;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthenticationResponse register(ResgisterRequest request) {

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = userRepository.save(user);

        var jwtToken = jwtService.generteToken(savedUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(savedUser.getFirstname())
                .lastname(savedUser.getFirstname())
                .email(savedUser.getEmail())

                .Message("User Added Successfully")
                .build();
    }
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generteToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getFirstname())
                .email(user.getEmail())
                .Message("User Connected")
                .build();

    }

}
