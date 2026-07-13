package com.hotel.booking.api.domain.hotel.web.request;

import jakarta.validation.constraints.*;

public record UpdateRoomRequest(
        @NotBlank(message = "Room type code must not be empty")
        String typeCode,
        @NotNull(message = "Room number is required")
        @Positive(message = "Room number must be greater than zero")
        @Max(value = 100, message = "Room number must not exceed 100")
        Integer number,
        @NotNull(message = "Room floor is required")
        @PositiveOrZero(message = "Floor must be greater than or equal to zero")
        @Max(value = 20, message = "Floor must not exceed 20")
        Integer floor
) {
}
