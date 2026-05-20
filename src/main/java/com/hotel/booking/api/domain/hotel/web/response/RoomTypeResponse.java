package com.hotel.booking.api.domain.hotel.web.response;

import java.math.BigDecimal;

public record RoomTypeResponse(
        String code,
        String hotelCode,
        String name,
        String description,
        Integer capacity,
        BigDecimal basePrice
) {
}
