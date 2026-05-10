package com.hotel.booking.api.domain.hotel.web.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateHotelRequest(
        @NotBlank(message = "Hotel name must not be empty")
        String name,
        @NotBlank(message = "Hotel address must not be empty")
        String address,
        @NotBlank(message = "Hotel city must not be empty")
        String city,
        @NotBlank(message = "Hotel country must not be empty")
        String country,
        @NotBlank(message = "Hotel phone must not be empty")
        String phone
) {
}
