package com.hotel.booking.api.domain.person.component;

import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.web.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    public final PersonMapper personMapper;

    public CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getCode(),
                personMapper.toResponse(customer.getPerson()),
                customer.getDocumentType(),
                customer.getDocumentNumber()
        );
    }

}
