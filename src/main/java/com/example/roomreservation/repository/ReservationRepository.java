package com.example.roomreservation.repository;

import com.example.roomreservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT r FROM Reservation r WHERE r.start >= :start AND r.end < :end")
    List<Reservation> findAllBetweenDatetimes(LocalDateTime start, LocalDateTime end);
}
