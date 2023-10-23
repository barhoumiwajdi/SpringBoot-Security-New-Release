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
@Table(name = "Hotels")
public class Hotel {

    @Id

    /*@NotEmpty(message = "Please provide a hotel name")*/
    private String hotelname;



    private String rating;



    private String price;

    private String hotelPhone;

    private String hotelTelephone;




    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Reservation> reservations;
}
