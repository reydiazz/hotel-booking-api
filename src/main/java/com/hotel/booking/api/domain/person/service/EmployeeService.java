package com.hotel.booking.api.domain.person.service;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.hotel.service.HotelService;
import com.hotel.booking.api.domain.person.component.EmployeeMapper;
import com.hotel.booking.api.domain.person.exception.EmployeeCodeAlreadyException;
import com.hotel.booking.api.domain.person.exception.PersonNotFoundException;
import com.hotel.booking.api.domain.person.model.entity.Employee;
import com.hotel.booking.api.domain.person.model.entity.Person;
import com.hotel.booking.api.domain.person.repository.EmployeeRepository;
import com.hotel.booking.api.domain.person.web.request.CreateEmployeeRequest;
import com.hotel.booking.api.domain.person.web.request.CreatePersonRequest;
import com.hotel.booking.api.domain.person.web.request.UpdateEmployeeRequest;
import com.hotel.booking.api.domain.person.web.response.EmployeeResponse;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    public static final String PREFIX = "EMP";
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final HotelService hotelService;
    private  final PersonService personService;

    @Transactional
    public EmployeeResponse create (CreateEmployeeRequest request){
        String code = CodeGenerator.next(PREFIX);
        Hotel hotel = hotelService.findByCodeOrThrow(request.hotelCode());
        Person person =personService.createEntity(request.person());
        Employee employee = new Employee(
                code,
                hotel,
                person,
                request.position(),
                request.salary()

        );
        try {
            Employee saved = repository.save(employee);
            return mapper.toResponse(saved);
        }catch (DataIntegrityViolationException e){
            throw  new EmployeeCodeAlreadyException();
        }
    }
    @Transactional
    public  EmployeeResponse update (String code,UpdateEmployeeRequest request){
        Employee employee = findByCodeOrThrow(code);
        Hotel hotel = hotelService.findByCodeOrThrow(request.hotelCode());
        Person person = personService.update(employee.getPerson().getCode(),request.person());
        employee.update(
                hotel,
                person,
                request.position(),
                request.salary()
        );
        return mapper.toResponse(employee);
    }
    @Transactional
    public  void  deleteByCode (String code){
        Employee employee = findByCodeOrThrow(code);
        personService.deleteByCode(employee.getPerson().getCode());
        repository.delete(employee);
    }

    public Employee findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new PersonNotFoundException(code)
        );
    }


}
