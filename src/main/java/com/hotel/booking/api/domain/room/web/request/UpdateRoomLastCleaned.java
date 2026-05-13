package com.hotel.booking.api.domain.room.web.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateRoomLastCleaned(
        @NotNull(message = "Room last cleaned date is required")
        LocalDateTime lastCleaned
) {
}
