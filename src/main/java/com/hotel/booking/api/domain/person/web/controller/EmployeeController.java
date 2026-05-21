package com.hotel.booking.api.domain.person.web.controller;

import com.hotel.booking.api.domain.person.service.EmployeeService;
import com.hotel.booking.api.domain.person.web.request.CreateEmployeeRequest;
import com.hotel.booking.api.domain.person.web.request.UpdateEmployeeRequest;
import com.hotel.booking.api.domain.person.web.response.EmployeeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> findAll(Pageable pageable) {
        Page<EmployeeResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hotel/{hotelCode}")
    public ResponseEntity<Page<EmployeeResponse>> findByHotel(@PathVariable String hotelCode, Pageable pageable) {
        Page<EmployeeResponse> response = service.findByHotel(hotelCode, pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody @Valid CreateEmployeeRequest request){
        EmployeeResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{code}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable String code, @RequestBody @Valid UpdateEmployeeRequest request){
        EmployeeResponse response = service.update(code, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code){
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

}
