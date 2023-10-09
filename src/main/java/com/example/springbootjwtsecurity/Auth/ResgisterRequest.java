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


public class ResgisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

}
