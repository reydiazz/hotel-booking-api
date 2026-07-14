package com.hotel.booking.api.domain.reservation.web.response;

import com.hotel.booking.api.domain.reservation.model.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationResponse(
        String code,
        String customerCode,
        String customerFullName,
        String userCode,
        String userFullName,
        ReservationStatus status,
        LocalDateTime checkIn,
        LocalDateTime checkout,
        LocalDateTime createdAt,
        List<ReservationRoomResponse> rooms
) {
}
