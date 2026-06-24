package com.hotel.booking.api.domain.auth.web.controller;

import com.hotel.booking.api.domain.auth.component.UserMapper;
import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.service.UserService;
import com.hotel.booking.api.domain.auth.web.request.CreateUserRequest;
import com.hotel.booking.api.domain.auth.web.request.UpdateUserRequest;
import com.hotel.booking.api.domain.auth.web.response.UserResponse;
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
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        Page<User> page = service.findAll(pageable);
        return ResponseEntity.ok(page.map(mapper::toResponse));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        User user = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(user));
    }

    @PatchMapping("/{code}/activate")
    public ResponseEntity<UserResponse> activate(@PathVariable String code) {
        User user = service.activate(code);
        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @PatchMapping("/{code}/deactivate")
    public ResponseEntity<UserResponse> deactivate(@PathVariable String code) {
        User user = service.deactivate(code);
        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @PutMapping("/{code}")
    public ResponseEntity<UserResponse> update(@PathVariable String code,@Valid @RequestBody UpdateUserRequest request) {
        User user = service.update(code, request);
        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        service.delete(code);
        return ResponseEntity.ok().build();
    }

}
