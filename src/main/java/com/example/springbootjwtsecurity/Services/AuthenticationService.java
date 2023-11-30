package com.example.springbootjwtsecurity.Services;


import com.example.springbootjwtsecurity.Auth.AuthenticationRequest;
import com.example.springbootjwtsecurity.Auth.AuthenticationResponse;
import com.example.springbootjwtsecurity.Auth.ResgisterRequest;
import com.example.springbootjwtsecurity.Config.JwtService;

import com.example.springbootjwtsecurity.Exception.BadRequestResponse;
import com.example.springbootjwtsecurity.Exception.CustomerNotFoundException;
import com.example.springbootjwtsecurity.Model.Role;
import com.example.springbootjwtsecurity.Model.Token;
import com.example.springbootjwtsecurity.Model.TokenType;
import com.example.springbootjwtsecurity.Model.User;
import com.example.springbootjwtsecurity.Repository.TokenRepo;
import com.example.springbootjwtsecurity.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor

public class AuthenticationService {
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder ;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepo tokenRepo;
    public AuthenticationResponse register(ResgisterRequest request) {

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .city(request.getCity())
                .streetAddress(request.getStreetAddress())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .state(request.getState())
                .role(Role.USER)

                .build();
        var jwtToken = jwtService.generteToken(user);
        var savedUser = userRepository.save(user);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(savedUser.getFirstname())
                .lastname(savedUser.getFirstname())
                .email(savedUser.getEmail())
                .city(savedUser.getCity())
                .streetAddress(savedUser.getStreetAddress())
                .country(savedUser.getCountry())
                .postalCode(savedUser.getPostalCode())
                .state(savedUser.getState())
                .role(Role.USER)
                .Message("User Added Successfully")
                .build();
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }
    public AuthenticationResponse login(AuthenticationRequest request) {
        Optional<User> exist = userRepository.findByEmail(request.getEmail());
        if(exist.isPresent()){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generteToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .id(user.getId())
                    .firstname(user.getFirstname())
                    .lastname(user.getFirstname())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .Message("User Connected")
                    .build();
        }
        else
        {
            throw new BadRequestResponse("verify your email and password");
         }


    }

}
