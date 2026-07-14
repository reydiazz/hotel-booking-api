package com.hotel.booking.api.domain.person.validation.document;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerDocumentValidator implements ConstraintValidator<ValidCustomerDocument, CustomerDocumentRequest> {

    @Override
    public boolean isValid(CustomerDocumentRequest request, ConstraintValidatorContext context) {
        if (request == null || request.documentType() == null || request.documentNumber() == null) {
            return true;
        }
        return switch (request.documentType()) {
            case DNI -> request.documentNumber().matches("\\d{8}");

            case PASSPORT -> request.documentNumber().matches("[A-Za-z0-9]{6,12}");

            case FOREIGN_ID -> request.documentNumber().matches("[A-Za-z0-9]{6,15}");

            case TAX_ID -> request.documentNumber().matches("\\d{11}");
        };
    }

}