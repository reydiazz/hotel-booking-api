package com.hotel.booking.api.domain.person.repository;

import com.hotel.booking.api.domain.person.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
