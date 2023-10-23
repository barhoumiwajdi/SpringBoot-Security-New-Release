package com.example.springbootjwtsecurity.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reservations")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    private LocalDate checkinDate;

    private LocalDate checkoutDate;

    private Integer noOfPerson;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Integer getNoOfPerson() {
        return noOfPerson;
    }

    public void setNoOfPerson(Integer noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public Boolean getReservationStatus() {
        return ReservationStatus;
    }

    public void setReservationStatus(Boolean reservationStatus) {
        ReservationStatus = reservationStatus;
    }

    private Boolean ReservationStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customers customer;
}
