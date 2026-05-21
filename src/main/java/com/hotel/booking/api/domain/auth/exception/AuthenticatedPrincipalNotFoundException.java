package com.hotel.booking.api.domain.auth.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class AuthenticatedPrincipalNotFoundException extends BusinessException {

    public AuthenticatedPrincipalNotFoundException() {
        super("Authenticated principal not found", AuthErrorCode.AUTHENTICATED_PRINCIPAL_NOT_FOUND);
    }

}
