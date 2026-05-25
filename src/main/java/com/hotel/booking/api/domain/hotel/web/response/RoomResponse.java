package com.hotel.booking.api.domain.hotel.web.response;

import com.hotel.booking.api.domain.hotel.model.enums.RoomStatus;

import java.time.LocalDateTime;

public record RoomResponse(
        String code,
        String roomTypeCode,
        String roomTypeName,
        Integer number,
        Integer floor,
        RoomStatus status,
        LocalDateTime lastCleaned
) {
}
