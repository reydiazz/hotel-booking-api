package com.hotel.booking.api.domain.person.service;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.hotel.service.HotelService;
import com.hotel.booking.api.domain.person.component.EmployeeMapper;
import com.hotel.booking.api.domain.person.exception.employee.EmployeeNotFoundException;
import com.hotel.booking.api.domain.person.model.entity.Employee;
import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.repository.EmployeeRepository;
import com.hotel.booking.api.domain.person.web.request.CreateEmployeeRequest;
import com.hotel.booking.api.domain.person.web.request.UpdateEmployeeRequest;
import com.hotel.booking.api.domain.person.web.response.EmployeeResponse;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    public static final String PREFIX = "EMP";
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    private final HotelService hotelService;
    private final PersonService personService;

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<EmployeeResponse> findByHotel(String hotelCode, Pageable pageable) {
        Hotel hotel = hotelService.findByCodeOrThrow(hotelCode);
        return repository.findByHotel(hotel,pageable).map(mapper::toResponse);
    }

    @Transactional
    public EmployeeResponse create(CreateEmployeeRequest request) {
        Hotel hotel = hotelService.findByCodeOrThrow(
                request.hotelCode()
        );
        Person person = personService.create(
                request.person()
        );
        String code = CodeGenerator.next(PREFIX);
        Employee employee = new Employee(
                code,
                hotel,
                person,
                request.position(),
                request.salary()
        );
        Employee saved = repository.save(employee);
        return mapper.toResponse(saved);
    }

    @Transactional
    public EmployeeResponse update(String code, UpdateEmployeeRequest request) {
        Employee employee = findByCodeOrThrow(code);
        Hotel hotel = hotelService.findByCodeOrThrow(
                request.hotelCode()
        );
        Person person = personService.update(
                employee.getPerson().getCode(),
                request.person()
        );
        employee.update(
                hotel,
                person,
                request.position(),
                request.salary()
        );
        return mapper.toResponse(employee);
    }

    @Transactional
    public void delete(String code) {
        Employee employee = findByCodeOrThrow(code);
        repository.delete(employee);
        personService.delete(
                employee.getPerson().getCode()
        );
    }

    public Employee findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new EmployeeNotFoundException(code)
        );
    }

}
