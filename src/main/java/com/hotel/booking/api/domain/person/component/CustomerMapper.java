package com.hotel.booking.api.domain.person.component;

import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.web.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final PersonMapper person;

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getCode(),
                customer.getDocumentType(),
                customer.getDocumentNumber(),
                person.toResponse(customer.getPerson())
        );
    }

}
