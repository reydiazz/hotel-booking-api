package com.hotel.booking.api.domain.person.service;

import com.hotel.booking.api.domain.person.component.CustomerMapper;
import com.hotel.booking.api.domain.person.exception.customer.CustomerNotFoundException;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.repository.CustomerRepository;
import com.hotel.booking.api.domain.person.web.request.CreateCustomerRequest;
import com.hotel.booking.api.domain.person.web.request.UpdateCustomerRequest;
import com.hotel.booking.api.domain.person.web.response.CustomerResponse;
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
    private final CustomerMapper mapper;

    private final PersonService personService;

    @Transactional(readOnly = true)
    public Page<CustomerResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional
    public CustomerResponse create(CreateCustomerRequest request) {
        String code = CodeGenerator.next((PREFIX));
        Person person = personService.create(
                request.person()
        );
        Customer customer = new Customer(
                code,
                person,
                request.documentType(),
                request.documentNumber()
        );
        Customer saved = repository.save(customer);
        return mapper.toResponse(saved);
    }

    @Transactional
    public CustomerResponse update(String code, UpdateCustomerRequest request) {
        Customer customer = findByCodeOrThrow(code);
        Person person = personService.update(
                customer.getPerson().getCode(),
                request.person()
        );
        customer.update(
                person,
                request.documentType(),
                request.documentNumber()
        );
        return mapper.toResponse(customer);

    }

    @Transactional
    public void delete(String code) {
        Customer customer = findByCodeOrThrow(code);
        personService.delete(
                customer.getPerson().getCode()
        );
        repository.delete(customer);
    }

    public Customer findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new CustomerNotFoundException(code)
        );
    }

}
