package com.hotel.booking.api.domain.auth.web.response;

public record LoginResponse(
        String token,
        AuthResponse auth
) {
}
