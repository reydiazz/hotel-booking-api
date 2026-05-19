package com.hotel.booking.api.domain.person.exception.customer;

import com.hotel.booking.api.shared.exception.BusinessException;

public class CustomerNotFoundException extends BusinessException {

    public CustomerNotFoundException(String code) {
        super("Customer with code " + code + " not found", CustomerErrorCode.NOT_FOUND);
    }

}
