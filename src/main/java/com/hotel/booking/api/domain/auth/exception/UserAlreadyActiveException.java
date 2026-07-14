package com.hotel.booking.api.domain.auth.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class UserAlreadyActiveException extends BusinessException {

    public UserAlreadyActiveException() {
        super("The user is already active", UserErrorCode.USER_ALREADY_ACTIVE);
    }

}
