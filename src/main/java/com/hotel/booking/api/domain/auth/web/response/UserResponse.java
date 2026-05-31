package com.hotel.booking.api.domain.auth.web.response;

import com.hotel.booking.api.domain.auth.model.enums.Role;

public record UserResponse(
        String code,
        String username,
        Role role,
        String fullName,
        String phone,
        boolean active
) {
}
