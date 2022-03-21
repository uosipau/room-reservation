package com.example.roomreservation.service;

import com.example.roomreservation.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationsByDate(LocalDate date);

    List<Reservation> getAllReservations();
}
