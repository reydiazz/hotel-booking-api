package com.hotel.booking.api.domain.person.web.request;

import com.hotel.booking.api.domain.person.validation.Adult;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CreatePersonRequest(
        @NotBlank(message = "Person first name not be empty")
        String firstName,
        @NotBlank(message = "Person last name not be empty")
        String lastName,
        @Pattern(regexp = "^(?:\\+51)?9\\d{8}$", message = "The mobile format is not valid.")
        String phone,
        @Adult
        LocalDate birthDate
) {
}