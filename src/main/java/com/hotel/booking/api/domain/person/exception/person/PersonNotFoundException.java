package com.hotel.booking.api.domain.person.exception.person;

import com.hotel.booking.api.shared.exception.BusinessException;

public class PersonNotFoundException extends BusinessException {

    public PersonNotFoundException(String code) {
        super("Person with code '%s' not found".formatted(code), PersonErrorCode.PERSON_NOT_FOUND);
    }

}
