package com.hotel.booking.api.domain.person.exception;

import com.hotel.booking.api.domain.hotel.exception.HotelErrorCode;
import com.hotel.booking.api.shared.exception.BusinessException;

public class PersonNotFoundException extends BusinessException {
    public PersonNotFoundException(String code) {
        super("Person with code " + code +" not found", PersonErrorCode.NOT_FOUND);
    }
}
