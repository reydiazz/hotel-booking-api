package com.hotel.booking.api.domain.person.service;


import com.hotel.booking.api.domain.person.component.PersonMapper;
import com.hotel.booking.api.domain.person.exception.PersonCodeAlreadyExistsException;
import com.hotel.booking.api.domain.person.exception.PersonHasRelationsException;
import com.hotel.booking.api.domain.person.exception.PersonNotFoundException;
import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.repository.PersonRepository;
import com.hotel.booking.api.domain.person.web.request.CreatePersonRequest;
import com.hotel.booking.api.domain.person.web.request.UpdatePersonRequest;
import com.hotel.booking.api.domain.person.web.response.PersonResponse;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    public static final String PREFIX = "PEO";
    private final PersonRepository repository;
    private final PersonMapper mapper;

    public PersonResponse create(CreatePersonRequest request) {
        return mapper.toResponse(createEntity(request));
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
    public void deleteByCode(String code) {
        Person person = findByCodeOrThrow(code);
        try {
            repository.delete(person);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new PersonHasRelationsException();
        }
    }
    @Transactional
    public Person createEntity (CreatePersonRequest request) {
        String code = CodeGenerator.next(PREFIX);
        Person person = new Person(
                code,
                request.firstName(),
                request.lastname(),
                request.phone(),
                request.birthDate()
        );
        try {
            return repository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw new PersonCodeAlreadyExistsException();
        }
    }


    public Person findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new PersonNotFoundException(code)
        );
    }


}
