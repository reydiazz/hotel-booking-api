package com.hotel.booking.api.domain.auth.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class UserAlreadyDeactivateException extends BusinessException {

    public UserAlreadyDeactivateException() {
        super("The user is already deactivated", UserErrorCode.USER_ALREADY_DEACTIVATE);
    }

}
