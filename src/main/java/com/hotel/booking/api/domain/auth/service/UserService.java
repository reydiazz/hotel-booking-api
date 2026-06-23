package com.hotel.booking.api.domain.auth.service;

import com.hotel.booking.api.domain.auth.exception.user.UserNotFoundException;
import com.hotel.booking.api.domain.auth.exception.user.UsernameAlreadyExistsException;
import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.repository.UserRepository;
import com.hotel.booking.api.domain.auth.web.request.CreateUserRequest;
import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.service.PersonService;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String PREFIX = "USR";
    private final UserRepository repository;

    private final PersonService personService;

    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public User create(CreateUserRequest request) {
        verifyUsername(request.username());
        String code = CodeGenerator.next(PREFIX);
        Person person = personService.create(request.person());
        String passwordCode = new BCryptPasswordEncoder().encode(request.password());
        User user = new User(code, request.username(), passwordCode, request.role(), person);
        return repository.save(user);
    }

    @Transactional
    public User deactivate(String code) {
        User user = findByCodeOrThrow(code);
        user.deactivate();
        return user;
    }

    @Transactional
    public User activate(String code) {
        User user = findByCodeOrThrow(code);
        user.activate();
        return user;
    }

    public User findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new UserNotFoundException(code)
        );
    }

    private void verifyUsername(String username) {
        if (repository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException(username);
        }
    }

}
