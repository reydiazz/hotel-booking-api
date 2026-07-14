package com.hotel.booking.api.domain.person.web.request;

import com.hotel.booking.api.domain.person.validation.adult.Adult;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CreatePersonRequest(
        @NotBlank(message = "Person first name not be empty")
        String firstName,
        @NotBlank(message = "Person last name not be empty")
        String lastName,
        @Pattern(regexp = "^\\+[1-9]\\d{7,14}$", message = "The phone number must include the country code")
        String phone,
        @Adult
        LocalDate birthDate
) {
}