package com.example.springbootjwtsecurity.Auth;

import com.example.springbootjwtsecurity.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthenticationResponse {
private int id ;
    private String token;
    private String firstname;
    private String lastname ;
    private String email ;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String Message;
    private Role role;

}
