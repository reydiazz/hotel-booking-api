package com.hotel.booking.api.domain.auth.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class NoAuthenticatedUserException extends BusinessException {

    public NoAuthenticatedUserException() {
        super("No authenticated user found in security context", AuthErrorCode.AUTHENTICATED_USER);
    }

}
