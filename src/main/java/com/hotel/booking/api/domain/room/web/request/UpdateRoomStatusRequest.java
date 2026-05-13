package com.hotel.booking.api.domain.room.web.request;

import com.hotel.booking.api.domain.room.model.enums.RoomStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateRoomStatusRequest(
        @NotNull(message = "Room status must not be empty")
        RoomStatus roomStatus
) {
}
