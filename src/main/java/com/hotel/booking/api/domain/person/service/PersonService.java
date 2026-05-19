package com.hotel.booking.api.domain.person.service;

import com.hotel.booking.api.domain.person.exception.person.PersonNotFoundException;
import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.repository.PersonRepository;
import com.hotel.booking.api.domain.person.web.request.CreatePersonRequest;
import com.hotel.booking.api.domain.person.web.request.UpdatePersonRequest;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    public static final String PREFIX = "PEO";
    private final PersonRepository repository;

    @Transactional
    public Person create(CreatePersonRequest request) {
        String code = CodeGenerator.next(PREFIX);
        Person person = new Person(
                code,
                request.firstName(),
                request.lastname(),
                request.phone(),
                request.birthDate()
        );
        return repository.save(person);
    }

    @Transactional
    public Person update(String code, UpdatePersonRequest request) {
        Person person = findByCodeOrThrow(code);
        person.update(
                request.firstName(),
                request.lastname(),
                request.phone(),
                request.birthDate()
        );
        return person;
    }

    @Transactional
    public void delete(String code) {
        Person person = findByCodeOrThrow(code);
        repository.delete(person);
    }

    public Person findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new PersonNotFoundException(code)
        );
    }

}
