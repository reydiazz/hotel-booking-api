package com.hotel.booking.api.domain.auth.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class UserSelfDeactivationException extends BusinessException {

    public UserSelfDeactivationException() {
        super("A user is not allowed to deactivate their own account.", UserErrorCode.USER_SELF_DEACTIVATION);
    }

}