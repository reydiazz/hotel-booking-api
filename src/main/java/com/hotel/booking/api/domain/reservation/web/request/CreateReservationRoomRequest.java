package com.hotel.booking.api.domain.reservation.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateReservationRoomRequest(
        @NotBlank(message = "Room code is required")
        String roomCode,
        @NotNull(message = "Room price per night is required")
        BigDecimal pricePerNight
) {
}
