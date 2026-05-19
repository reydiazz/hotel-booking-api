package com.hotel.booking.api.domain.person.web.response;

import java.time.LocalDate;

public record PersonResponse(
        String firstName,
        String lastName,
        String phone,
        LocalDate birthDate
) {
}
