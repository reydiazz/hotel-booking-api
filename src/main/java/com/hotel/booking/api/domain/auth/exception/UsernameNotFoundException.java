package com.hotel.booking.api.domain.auth.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class UsernameNotFoundException extends BusinessException {

    public UsernameNotFoundException(String username) {
        super("User with username '%s' not found".formatted(username), UserErrorCode.USERNAME_NOT_FOUND);
    }

}
