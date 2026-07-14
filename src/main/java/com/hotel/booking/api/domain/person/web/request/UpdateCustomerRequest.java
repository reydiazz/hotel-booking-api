package com.hotel.booking.api.domain.person.web.request;

import com.hotel.booking.api.domain.person.model.enums.DocumentTypeCustomer;
import com.hotel.booking.api.domain.person.validation.document.CustomerDocumentRequest;
import com.hotel.booking.api.domain.person.validation.document.ValidCustomerDocument;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ValidCustomerDocument
public record UpdateCustomerRequest(
        @NotNull(message = "Customer data is required")
        @Valid
        UpdatePersonRequest person,
        @NotNull(message = "Customer document type not be empty")
        DocumentTypeCustomer documentType,
        @NotBlank(message = "Customer document number not be empty")
        String documentNumber
) implements CustomerDocumentRequest {
}
