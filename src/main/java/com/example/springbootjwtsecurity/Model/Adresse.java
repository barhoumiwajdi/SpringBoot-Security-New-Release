package com.example.springbootjwtsecurity.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {


    private String streetAddress;

    private String city;

    private String state;

    private String postalCode;

    private String country;

}
