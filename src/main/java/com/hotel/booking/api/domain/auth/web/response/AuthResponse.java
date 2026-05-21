package com.hotel.booking.api.domain.auth.web.response;

import com.hotel.booking.api.domain.auth.model.enums.Role;

public record AuthResponse(
        String code,
        String username,
        String firstName,
        String lastName,
        Role role
) {
}
