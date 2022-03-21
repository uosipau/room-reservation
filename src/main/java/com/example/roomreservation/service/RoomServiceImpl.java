package com.example.roomreservation.service;

import com.example.roomreservation.entity.Reservation;
import com.example.roomreservation.entity.Room;
import com.example.roomreservation.exception.RoomIsNotAvailableException;
import com.example.roomreservation.exception.RoomNotFoundException;
import com.example.roomreservation.repository.ReservationRepository;
import com.example.roomreservation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.roomreservation.util.TimeUtils.timeSpansIntersects;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public void reserveRoom(Long roomId, LocalDateTime start, LocalDateTime end) throws RoomNotFoundException, RoomIsNotAvailableException {
        final var room = roomRepository.findById(roomId)
                .orElseThrow(RoomNotFoundException::new);
        final var reserved = room.getReservations().stream()
                .anyMatch(res -> timeSpansIntersects(res.getStart(), res.getEnd(), start, end));
        if (reserved) {
            throw new RoomIsNotAvailableException();
        }
        reservationRepository.save(new Reservation(room, start, end));
    }

    @Override
    public List<Room> getAvailableRooms(
            LocalDateTime start,
            LocalDateTime end,
            int attendees,
            boolean multimedia,
            Long buildingId
    ) {
        return roomRepository.findAllAvailable(start, end, attendees, multimedia, buildingId);
    }
}
