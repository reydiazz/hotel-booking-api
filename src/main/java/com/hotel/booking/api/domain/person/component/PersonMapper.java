package com.hotel.booking.api.domain.person.component;

import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.web.response.PersonResponse;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonResponse toResponse(Person person) {
        return new PersonResponse(
                person.getFirstName(),
                person.getLastName(),
                person.getPhone(),
                person.getBirthDate()
        );
    }
    
}
