package com.hotel.booking.api.domain.auth.exception.user;

import com.hotel.booking.api.shared.exception.BusinessException;

public class UsernameAlreadyExistsException extends BusinessException {

    public UsernameAlreadyExistsException(String username) {
        super("User with name '%s' already exists".formatted(username), UserErrorCode.USER_ALREADY_EXISTS);
    }

}
