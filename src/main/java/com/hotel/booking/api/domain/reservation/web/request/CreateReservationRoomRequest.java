package com.hotel.booking.api.domain.reservation.web.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReservationRoomRequest(
        @NotBlank(message = "Room code is required")
        String roomCode,
        @NotNull(message = "The number of nights is required")
        @Min(value = 1, message = "The number of nights must be at least 1")
        Integer nights
) {
}
