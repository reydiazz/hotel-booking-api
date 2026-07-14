package com.hotel.booking.api.domain.person.exception.customer;

import com.hotel.booking.api.shared.exception.BusinessException;

public class CustomerDocumentAlreadyExistsException extends BusinessException {

    public CustomerDocumentAlreadyExistsException() {
        super("A customer with this document already exists", CustomerErrorCode.CUSTOMER_DOCUMENT_ALREADY_EXISTS);
    }

}
