package com.hotel.booking.api.domain.person.web.response;

import com.hotel.booking.api.domain.person.model.enums.DocumentTypeCustomer;

public record CustomerResponse(
        String code,
        PersonResponse person,
        DocumentTypeCustomer documentType,
        String documentNumber
) {
}
