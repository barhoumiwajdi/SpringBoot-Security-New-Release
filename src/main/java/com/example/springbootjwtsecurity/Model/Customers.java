package com.example.springbootjwtsecurity.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customers {


    @Id
    @GeneratedValue
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;


    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

}
