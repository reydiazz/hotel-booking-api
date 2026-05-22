package com.hotel.booking.api.domain.reservation.web.response;

import com.hotel.booking.api.domain.person.web.response.CustomerResponse;
import com.hotel.booking.api.domain.reservation.model.enums.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(
        String code,
        CustomerResponse customer,
        String username,
        String employeeName,
        ReservationStatus status,
        LocalDateTime checkIn,
        LocalDateTime checkout,
        LocalDateTime createdAt
) {
}
