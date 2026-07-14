package com.hotel.booking.api.domain.reservation.web.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservationRoomResponse(
        String code,
        Integer number,
        Integer floor,
        String typeName,
        BigDecimal pricePerNight,
        LocalDateTime checkIn,
        LocalDateTime checkOut,
        Long nights
) {
}
