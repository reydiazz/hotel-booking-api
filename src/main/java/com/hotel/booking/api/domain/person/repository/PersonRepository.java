package com.hotel.booking.api.domain.person.repository;

import com.hotel.booking.api.domain.person.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
}
