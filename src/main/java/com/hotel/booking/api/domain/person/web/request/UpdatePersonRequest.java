package com.hotel.booking.api.domain.person.web.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdatePersonRequest(
        @NotBlank(message = "Person first name not be empty")
        String firstName,
        @NotBlank(message = "Person last name not be empty")
        String lastname,
        String phone,
        LocalDate birthDate
) {
}
