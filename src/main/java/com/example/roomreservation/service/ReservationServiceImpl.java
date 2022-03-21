package com.example.roomreservation.service;

import com.example.roomreservation.entity.Reservation;
import com.example.roomreservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getReservationsByDate(LocalDate date) {
        final var start = date.atStartOfDay();
        final var end = date.plusDays(1).atStartOfDay();
        return reservationRepository.findAllBetweenDatetimes(start, end);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
