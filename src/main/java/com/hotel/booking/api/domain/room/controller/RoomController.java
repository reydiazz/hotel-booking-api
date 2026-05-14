package com.hotel.booking.api.domain.room.controller;

import com.hotel.booking.api.domain.room.service.RoomService;
import com.hotel.booking.api.domain.room.web.request.CreateRoomRequest;
import com.hotel.booking.api.domain.room.web.request.UpdateRoomRequest;
import com.hotel.booking.api.domain.room.web.request.UpdateRoomStatusRequest;
import com.hotel.booking.api.domain.room.web.response.RoomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<RoomResponse> deleteByCode(@PathVariable String code) {
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

}
