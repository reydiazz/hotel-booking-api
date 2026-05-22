package com.hotel.booking.api.domain.reservation.web.response;

import com.hotel.booking.api.domain.hotel.web.response.RoomResponse;

import java.math.BigDecimal;

public record ReservationRoomResponse(
        String code,
        RoomResponse room,
        BigDecimal pricePerNight
) {
}
