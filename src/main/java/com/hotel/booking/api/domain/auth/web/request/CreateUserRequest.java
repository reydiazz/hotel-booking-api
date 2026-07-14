package com.hotel.booking.api.domain.auth.web.request;

import com.hotel.booking.api.domain.auth.model.enums.Role;
import com.hotel.booking.api.domain.person.web.request.CreatePersonRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(
        @NotNull(message = "Person must not be null")
        @Valid
        CreatePersonRequest person,
        @NotBlank(message = "Username must not be empty")
        String username,
        @NotBlank(message = "Password must not be empty")
        String password,
        @NotNull(message = "Role must not be null")
        Role role
) {
}
