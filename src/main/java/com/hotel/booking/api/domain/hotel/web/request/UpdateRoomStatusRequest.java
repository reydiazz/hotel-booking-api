package com.hotel.booking.api.domain.hotel.web.request;

import com.hotel.booking.api.domain.hotel.model.enums.RoomStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateRoomStatusRequest(
        @NotNull(message = "Room status must not be empty")
        RoomStatus status
) {
}
