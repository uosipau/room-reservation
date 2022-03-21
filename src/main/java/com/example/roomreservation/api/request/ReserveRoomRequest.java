package com.example.roomreservation.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ReserveRoomRequest(
        @JsonProperty("room_id")
        long roomId,
        LocalDateTime start,
        LocalDateTime end
) {
}
