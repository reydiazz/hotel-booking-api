package com.hotel.booking.api.domain.reservation.web.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateReservationRequest(
        @NotBlank(message = "Reservation customer code is required")
        String customerCode,
        @NotNull(message = "Reservation rooms is required")
        @Valid
        List<CreateReservationRoomRequest> rooms
) {
}
