package com.hotel.booking.api.domain.hotel.web.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateRoomTypeRequest(
        @NotBlank(message = "Room type name must not be empty")
        String name,
        String description,
        @NotNull(message = "Room type capacity is required")
        @Min(value = 1, message = "Room type capacity must be greater than zero")
        @Max(value = 10, message = "Room type capacity must not exceed 10")
        Integer capacity,
        @NotNull(message = "Room type base price is required")
        @DecimalMin(value = "0.01", message = "Room type base price must be greater than zero")
        BigDecimal basePrice
) {
}
