package com.hotel.booking.api.domain.reservation.web.response;

import com.hotel.booking.api.domain.reservation.model.enums.ReservationStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ReservationResponse(
        String code,
        String customerFullName,
        String userFullName,
        ReservationStatus status,
        LocalDateTime createdAt,
        BigDecimal totalAmount,
        List<ReservationRoomResponse> rooms
) {
}
