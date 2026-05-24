package com.hotel.booking.api.domain.reservation.web.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateReservationRoomRequest(
        @NotBlank(message = "Room code is required")
        String roomCode,
        @NotNull(message = "Room price per night is required")
        @DecimalMin(value = "0.01", message = "Price per night must be greater than zero")
        BigDecimal pricePerNight,
        @NotNull(message = "The number of nights is required")
        @Min(value = 1, message = "The number of nights must be at least 1")
        Integer nights
) {
}
