package com.hotel.booking.api.domain.person.repository;

import com.hotel.booking.api.domain.person.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, String> {
}
