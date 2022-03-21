package com.example.roomreservation.repository;

import com.example.roomreservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    Optional<Room> findById(Long id);

    @Query("FROM Room r WHERE r.id NOT IN (" +
            "SELECT res.room FROM Reservation res WHERE " +
            "(:start >= res.start and :start < res.end) " +
            "OR" +
            "(:end > res.start and :end <= res.end)" +
            ")" +
            "AND r.maxAllocation >= :maxAllocation " +
            "AND r.multimedia = :multimedia " +
            "AND (:buildingId IS NULL OR r.buildingId = :buildingId) " +
            "ORDER BY r.maxAllocation")
    List<Room> findAllAvailable(
            LocalDateTime start,
            LocalDateTime end,
            int maxAllocation,
            boolean multimedia,
            Long buildingId
    );
}
