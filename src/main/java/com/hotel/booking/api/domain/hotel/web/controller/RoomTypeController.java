package com.hotel.booking.api.domain.hotel.web.controller;

import com.hotel.booking.api.domain.hotel.component.RoomTypeMapper;
import com.hotel.booking.api.domain.hotel.model.entity.RoomType;
import com.hotel.booking.api.domain.hotel.service.RoomTypeService;
import com.hotel.booking.api.domain.hotel.web.request.CreateRoomTypeRequest;
import com.hotel.booking.api.domain.hotel.web.request.UpdateRoomTypeRequest;
import com.hotel.booking.api.domain.hotel.web.response.RoomTypeResponse;
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
@RequestMapping("/api/room-types")
public class RoomTypeController {

    private final RoomTypeService service;
    private final RoomTypeMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTION')")
    public ResponseEntity<Page<RoomTypeResponse>> findAll(Pageable pageable) {
        Page<RoomType> page = service.findAll(pageable);
        return ResponseEntity.ok(page.map(mapper::toResponse));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RoomTypeResponse> create(@RequestBody @Valid CreateRoomTypeRequest request) {
        RoomType type = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(type));
    }

    @PutMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RoomTypeResponse> update(@PathVariable String code, @RequestBody @Valid UpdateRoomTypeRequest request) {
        RoomType type = service.update(code, request);
        return ResponseEntity.ok(mapper.toResponse(type));
    }

    @DeleteMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

}
