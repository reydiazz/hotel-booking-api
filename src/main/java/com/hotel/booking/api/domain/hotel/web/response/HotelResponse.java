package com.hotel.booking.api.domain.hotel.web.response;

public record HotelResponse(
        String code,
        String name,
        String address,
        String city,
        String country,
        String phone
) {
}
