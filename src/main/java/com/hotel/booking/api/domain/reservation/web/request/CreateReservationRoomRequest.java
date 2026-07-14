package com.hotel.booking.api.domain.reservation.web.request;

import jakarta.validation.constraints.NotBlank;

public record CreateReservationRoomRequest(
        @NotBlank(message = "Room code is required")
        String roomCode
) {
}
