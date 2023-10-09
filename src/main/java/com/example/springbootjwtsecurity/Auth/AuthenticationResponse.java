package com.example.springbootjwtsecurity.Auth;


import com.example.springbootjwtsecurity.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {


    private String token;
    private String firstname;
    private String lastname ;
    private String email ;
    private String Message;

}
