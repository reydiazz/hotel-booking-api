package com.hotel.booking.api.domain.auth.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String code) {
        super("User with code '%s' not found".formatted(code), UserErrorCode.USER_NOT_FOUND);
    }

}
