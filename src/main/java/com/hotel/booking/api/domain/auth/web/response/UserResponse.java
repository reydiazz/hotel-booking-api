package com.hotel.booking.api.domain.auth.web.response;

import com.hotel.booking.api.domain.auth.model.enums.Role;
import com.hotel.booking.api.domain.person.web.response.PersonResponse;

public record UserResponse(
        String code,
        String username,
        Role role,
        boolean active,
        PersonResponse person
) {
}
