package com.hotel.booking.api.domain.room.web.response;

import com.hotel.booking.api.domain.room.model.enums.RoomStatus;
import com.hotel.booking.api.domain.roomtype.web.response.RoomTypeResponse;

import java.time.LocalDateTime;

public record RoomResponse(
        String code,
        String hotelCode,
        RoomTypeResponse type,
        Integer number,
        Integer floor,
        RoomStatus status,
        LocalDateTime lastCleaned
) {
}
