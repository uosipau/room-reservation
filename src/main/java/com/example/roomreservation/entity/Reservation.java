package com.example.roomreservation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start_datetime")
    private LocalDateTime start;

    @Column(name = "end_datetime")
    private LocalDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Room room;

    public Reservation(Room room, LocalDateTime start, LocalDateTime end) {
        this.room = room;
        this.start = start;
        this.end = end;
    }
}