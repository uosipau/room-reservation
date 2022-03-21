package com.example.roomreservation.service;

import com.example.roomreservation.entity.Room;
import com.example.roomreservation.exception.RoomIsNotAvailableException;
import com.example.roomreservation.exception.RoomNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
    void reserveRoom(Long roomId, LocalDateTime start, LocalDateTime end) throws RoomNotFoundException, RoomIsNotAvailableException;

    List<Room> getAvailableRooms(
            LocalDateTime start,
            LocalDateTime end,
            int attendees,
            boolean multimedia,
            Long buildingId
    );
}
