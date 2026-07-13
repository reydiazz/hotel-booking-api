package com.hotel.booking.api;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.model.enums.Role;
import com.hotel.booking.api.domain.auth.repository.UserRepository;
import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.service.PersonService;
import com.hotel.booking.api.domain.person.web.request.CreatePersonRequest;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;


@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PersonService personService;

	@Test
	void createUser(){

		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "+51 987 654 321";
		LocalDate birthDate = LocalDate.of(2005, 11, 5);

		String PREFIX_USER = "USR";
		String code = CodeGenerator.next(PREFIX_USER);
		String username = "U23226030";
		String password = "root";
		Role role = Role.ADMIN;

		CreatePersonRequest request = new CreatePersonRequest(
				firstName,
				lastName,
				phoneNumber,
				birthDate
		);

		Person person = personService.create(request);
		String passwordByCrypt = new BCryptPasswordEncoder().encode(password);

		User user = new User(
				code,
				username,
				passwordByCrypt,
				role,
				person);

		userRepository.save(user);
	}

}
