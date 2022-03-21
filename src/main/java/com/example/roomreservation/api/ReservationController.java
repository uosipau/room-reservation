package com.example.roomreservation.api;

import com.example.roomreservation.api.request.ReserveRoomRequest;
import com.example.roomreservation.entity.Reservation;
import com.example.roomreservation.entity.Room;
import com.example.roomreservation.exception.RoomIsNotAvailableException;
import com.example.roomreservation.exception.RoomNotFoundException;
import com.example.roomreservation.service.ReservationService;
import com.example.roomreservation.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public record ReservationController(
        ReservationService reservationService,
        RoomService roomService
) {

    @GetMapping("/reservations")
    @ResponseBody
    public List<Reservation> getReservationsByDate(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return date == null
                ? reservationService.getAllReservations()
                : reservationService.getReservationsByDate(date);
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<Room> getAvailableRooms(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime end,
            @RequestParam Integer attendees,
            @RequestParam Boolean multimedia,
            @RequestParam(required = false, name = "building_id") Long buildingId
    ) {
        return roomService.getAvailableRooms(start, end, attendees, multimedia, buildingId);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Void> reserveRoom(@RequestBody ReserveRoomRequest request) {
        try {
            roomService.reserveRoom(request.roomId(), request.start(), request.end());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RoomIsNotAvailableException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (RoomNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
