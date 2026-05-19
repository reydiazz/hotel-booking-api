package com.hotel.booking.api.domain.person.exception;

import com.hotel.booking.api.domain.hotel.exception.HotelErrorCode;
import com.hotel.booking.api.shared.exception.BusinessException;

public class PersonHasRelationsException extends BusinessException {
    public PersonHasRelationsException() {
        super("Person cannot be deleted because it is associated with other entities", HotelErrorCode.HAS_RELATIONS);
    }
}
