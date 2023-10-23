package com.example.springbootjwtsecurity.Model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {




    @PrePersist
    protected void Oncreate(){
        this.created_at = new Date(System.currentTimeMillis());

    }
    @PreUpdate
    protected void Onupdate(){
        this.updated_at = new Date(System.currentTimeMillis());

    }
    @Id
    @GeneratedValue
    private int id ;
    private String firstname ;
    private String lastname;
    private String email;
    private String password ;
    private String mobile;
    private Date created_at;
    private Date updated_at;
    @Enumerated(EnumType.STRING)
    private Role role ;

    private String streetAddress;

    private String city;

    private String state;

    private String postalCode;

    private String country;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role.name()));

    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
