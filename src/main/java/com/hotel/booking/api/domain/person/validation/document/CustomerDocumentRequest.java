package com.hotel.booking.api.domain.person.validation.document;

import com.hotel.booking.api.domain.person.model.enums.DocumentTypeCustomer;

public interface CustomerDocumentRequest {

    DocumentTypeCustomer documentType();

    String documentNumber();

}
