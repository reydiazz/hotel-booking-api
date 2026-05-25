package com.hotel.booking.api.domain.reservation.web.response;

import java.math.BigDecimal;

public record ReservationRoomResponse(
        String code,
        Integer number,
        Integer floor,
        String typeName,
        BigDecimal pricePerNight,
        Integer nights
) {
}
