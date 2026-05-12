package com.hotel.booking.api.domain.roomtype.web.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateRoomTypeRequest(
        @NotBlank(message = "Room type hotel code must not be empty")
        String hotelCode,
        @NotBlank(message = "Room type name must not be empty")
        String name,
        @NotBlank(message = "Room type description must not be empty")
        String description,
        @NotNull(message = "Room type capacity must not be empty")
        @Min(value = 1, message = "Room type capacity must be greater than zero")
        Integer capacity,
        @NotNull(message = "Room type base price must not be empty")
        @DecimalMin(value = "0.01", message = "Room type base price must be greater than zero")
        BigDecimal basePrice
) {
}
