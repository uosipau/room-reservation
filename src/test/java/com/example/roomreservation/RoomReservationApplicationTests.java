package com.example.roomreservation;

import com.example.roomreservation.service.ReservationService;
import com.example.roomreservation.service.RoomService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoomReservationApplicationTests {

    @Autowired
    private RoomService roomService;
    @Autowired
    private ReservationService reservationService;

    // fast-written test for concurrency reservations. local use only
    @Test
    @Disabled
    void testConcurrentReservations() {
        int initReservations = 11;
        int initRooms = 7;

        final var start = LocalDateTime.now().plusYears(5);
        final var end = start.plusHours(1);
        List<Thread> threads = new ArrayList<>(initRooms * 2);
        for (long roomId = 1; roomId <= initRooms; roomId++) {
            threads.add(reserveRoomThread(roomId, start, end));
            threads.add(reserveRoomThread(roomId, start, end));
        }

        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        assertEquals(initReservations + initRooms, reservationService.getAllReservations().size());
    }

    private Thread reserveRoomThread(long roomId, LocalDateTime start, LocalDateTime end) {
        return new Thread(() -> {
            try {
                roomService.reserveRoom(roomId, start, end);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }

}
