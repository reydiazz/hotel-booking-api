package com.hotel.booking.api.domain.room.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateRoomRequest(
        @NotBlank(message = "Room type code must not be empty")
        String typeCode,
        @NotNull(message = "Room number is required")
        @Positive(message = "Room number must be greater than zero")
        Integer number,
        @NotNull(message = "Room floor is required")
        @PositiveOrZero(message = "Floor must be greater than or equal to zero")
        Integer floor

) {
}
