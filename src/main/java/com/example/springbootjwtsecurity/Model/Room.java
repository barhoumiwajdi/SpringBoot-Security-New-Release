package com.example.springbootjwtsecurity.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private Integer roomNumber;


    private String Roomtype;

    private Integer noOfPerson;

    private BigDecimal price;

    private Boolean available;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservation> reservations;
}
