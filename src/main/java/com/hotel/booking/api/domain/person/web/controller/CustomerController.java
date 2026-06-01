package com.hotel.booking.api.domain.person.web.controller;

import com.hotel.booking.api.domain.person.component.CustomerMapper;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.service.CustomerService;
import com.hotel.booking.api.domain.person.web.request.CreateCustomerRequest;
import com.hotel.booking.api.domain.person.web.request.UpdateCustomerRequest;
import com.hotel.booking.api.domain.person.web.response.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;
    private final CustomerMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTION')")
    public ResponseEntity<Page<CustomerResponse>> findAll(Pageable pageable) {
        Page<Customer> page = service.findAll(pageable);
        return ResponseEntity.ok(page.map(mapper::toResponse));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTION')")
    public ResponseEntity<CustomerResponse> create(@RequestBody @Valid CreateCustomerRequest request) {
        Customer customer = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(customer));
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTION')")
    public ResponseEntity<CustomerResponse> update(@PathVariable String code, @RequestBody @Valid UpdateCustomerRequest request) {
        Customer customer = service.update(code, request);
        return ResponseEntity.ok(mapper.toResponse(customer));
    }

    @DeleteMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

}
