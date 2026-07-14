package com.hotel.booking.api.domain.person.repository;

import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.model.enums.DocumentTypeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByDocumentTypeAndDocumentNumber(DocumentTypeCustomer documentType, String documentNumber);

    boolean existsByDocumentTypeAndDocumentNumberAndCodeNot(DocumentTypeCustomer documentType, String documentNumber, String code);

}
