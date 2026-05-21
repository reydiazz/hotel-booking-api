package com.hotel.booking.api.domain.hotel.web.controller;

import com.hotel.booking.api.domain.hotel.service.RoomService;
import com.hotel.booking.api.domain.hotel.web.request.CreateRoomRequest;
import com.hotel.booking.api.domain.hotel.web.request.UpdateRoomRequest;
import com.hotel.booking.api.domain.hotel.web.request.UpdateRoomStatusRequest;
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
@PreAuthorize("hasAnyRole('ADMIN','RECEPTION')")
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService service;

    @GetMapping
    public ResponseEntity<Page<RoomResponse>> findAll(Pageable pageable) {
        Page<RoomResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<RoomResponse> findByCode(@PathVariable String code) {
        RoomResponse response = service.findByCode(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hotel/{hotelCode}")
    public ResponseEntity<Page<RoomResponse>> findByHotel(@PathVariable String hotelCode, Pageable pageable) {
        Page<RoomResponse> response = service.findByHotel(hotelCode, pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@RequestBody @Valid CreateRoomRequest request) {
        RoomResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{code}")
    public ResponseEntity<RoomResponse> update(@PathVariable String code, @RequestBody @Valid UpdateRoomRequest request) {
        RoomResponse response = service.update(code, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{code}/room-status")
    public ResponseEntity<RoomResponse> updateRoomStatus(@PathVariable String code, @RequestBody @Valid UpdateRoomStatusRequest request) {
        RoomResponse response = service.updateRoomStatus(code, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{code}/clean")
    public ResponseEntity<RoomResponse> updateLastCleaned(@PathVariable String code) {
        RoomResponse response = service.updateLastCleaned(code);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<RoomResponse> delete(@PathVariable String code) {
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

}
