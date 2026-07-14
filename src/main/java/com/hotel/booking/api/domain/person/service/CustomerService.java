package com.hotel.booking.api.domain.person.service;

import com.hotel.booking.api.domain.person.exception.customer.CustomerDocumentAlreadyExistsException;
import com.hotel.booking.api.domain.person.exception.customer.CustomerNotFoundException;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.model.enums.DocumentTypeCustomer;
import com.hotel.booking.api.domain.person.repository.CustomerRepository;
import com.hotel.booking.api.domain.person.web.request.CreateCustomerRequest;
import com.hotel.booking.api.domain.person.web.request.UpdateCustomerRequest;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    public static final String PREFIX = "CUS";
    private final CustomerRepository repository;

    private final PersonService personService;

    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public Customer create(CreateCustomerRequest request) {
        validateDocumentNotExists(request.documentType(), request.documentNumber());
        String code = CodeGenerator.next((PREFIX));
        Person person = personService.create(request.person());
        Customer customer = new Customer(code, person, request.documentType(), request.documentNumber());
        return repository.save(customer);
    }

    @Transactional
    public Customer update(String code, UpdateCustomerRequest request) {
        validateDocumentNotExists(request.documentType(), request.documentNumber(), code);
        Customer customer = findByCodeOrThrow(code);
        Person person = personService.update(customer.getPerson().getCode(), request.person());
        customer.update(person, request.documentType(), request.documentNumber());
        return customer;
    }

    @Transactional
    public void delete(String code) {
        Customer customer = findByCodeOrThrow(code);
        repository.delete(customer);
        personService.delete(customer.getPerson().getCode());
    }

    public Customer findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new CustomerNotFoundException(code)
        );
    }

    private void validateDocumentNotExists(DocumentTypeCustomer documentType, String documentNumber) {
        if (repository.existsByDocumentTypeAndDocumentNumber(documentType, documentNumber)) {
            throw new CustomerDocumentAlreadyExistsException();
        }
    }

    private void validateDocumentNotExists(DocumentTypeCustomer documentType, String documentNumber, String code) {
        if (repository.existsByDocumentTypeAndDocumentNumberAndCodeNot(documentType, documentNumber, code)) {
            throw new CustomerDocumentAlreadyExistsException();
        }
    }

}
