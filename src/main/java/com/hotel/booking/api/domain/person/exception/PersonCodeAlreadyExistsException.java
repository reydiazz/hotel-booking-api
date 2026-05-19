package com.hotel.booking.api.domain.person.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class PersonCodeAlreadyExistsException extends BusinessException {
    public PersonCodeAlreadyExistsException() {
        super("Person with this code already exists", PersonErrorCode.CODE_ALREADY_EXISTS);
    }
}
