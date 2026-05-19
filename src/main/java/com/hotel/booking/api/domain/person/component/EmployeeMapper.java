package com.hotel.booking.api.domain.person.component;

import com.hotel.booking.api.domain.person.model.entity.Employee;
import com.hotel.booking.api.domain.person.web.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final PersonMapper personMapper;

    public EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getCode(),
                employee.getHotel().getCode(),
                personMapper.toResponse(employee.getPerson()),
                employee.getPosition(),
                employee.getSalary()
        );
    }

}
