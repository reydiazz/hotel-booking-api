package com.hotel.booking.api.domain.reservation.web.response;

import com.hotel.booking.api.domain.reservation.model.enums.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(
        String code,
        String customerCode,
        String customerFullName,
        String userCode,
        String userFullName,
        ReservationStatus status,
        LocalDateTime checkIn,
        LocalDateTime checkout,
        LocalDateTime createdAt
) {
}
