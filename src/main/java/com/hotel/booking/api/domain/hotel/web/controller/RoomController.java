package com.hotel.booking.api.domain.hotel.web.controller;

import com.hotel.booking.api.domain.hotel.component.RoomMapper;
import com.hotel.booking.api.domain.hotel.model.entity.Room;
import com.hotel.booking.api.domain.hotel.service.RoomService;
import com.hotel.booking.api.domain.hotel.web.request.CreateRoomRequest;
import com.hotel.booking.api.domain.hotel.web.request.UpdateRoomRequest;
import com.hotel.booking.api.domain.hotel.web.response.RoomResponse;
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
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService service;
    private final RoomMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTION')")
    public ResponseEntity<Page<RoomResponse>> findAll(Pageable pageable) {
        Page<Room> page = service.findAll(pageable);
        return ResponseEntity.ok(page.map(mapper::toResponse));
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTION')")
    public ResponseEntity<RoomResponse> findByCode(@PathVariable String code) {
        Room room = service.findByCode(code);
        return ResponseEntity.ok(mapper.toResponse(room));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RoomResponse> create(@RequestBody @Valid CreateRoomRequest request) {
        Room room = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(room));
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RoomResponse> update(@PathVariable String code, @RequestBody @Valid UpdateRoomRequest request) {
        Room room = service.update(code, request);
        return ResponseEntity.ok(mapper.toResponse(room));
    }

    @PatchMapping("/{code}/clean")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTION')")
    public ResponseEntity<RoomResponse> clean(@PathVariable String code) {
        Room room = service.clean(code);
        return ResponseEntity.ok(mapper.toResponse(room));
    }

    @PatchMapping("/{code}/out-of-service")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTION')")
    public ResponseEntity<RoomResponse> markOutOfService(@PathVariable String code) {
        Room room = service.markOutOfService(code);
        return ResponseEntity.ok(mapper.toResponse(room));
    }

    @DeleteMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

}
