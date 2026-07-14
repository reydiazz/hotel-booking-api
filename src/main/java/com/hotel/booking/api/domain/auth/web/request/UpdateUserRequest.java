package com.hotel.booking.api.domain.auth.web.request;

import com.hotel.booking.api.domain.auth.model.enums.Role;
import com.hotel.booking.api.domain.person.web.request.UpdatePersonRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        @NotNull(message = "Person must not be null")
        @Valid
        UpdatePersonRequest person,
        @NotNull(message = "Role must not be null")
        Role role
) {
}
