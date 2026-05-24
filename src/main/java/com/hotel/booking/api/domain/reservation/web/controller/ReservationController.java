package com.hotel.booking.api.domain.reservation.web.controller;

import com.hotel.booking.api.domain.reservation.service.ReservationService;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRequest;
import com.hotel.booking.api.domain.reservation.web.response.ReservationResponse;
import com.hotel.booking.api.domain.reservation.web.response.ReservationRoomResponse;
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
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService service;

    @GetMapping
    public ResponseEntity<Page<ReservationResponse>> findAll(Pageable pageable) {
        Page<ReservationResponse> response =  service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{code}")
    public ResponseEntity<ReservationRoomResponse> findDetailByCode(@PathVariable String code){
        ReservationRoomResponse response = service.findDetailByCode(code);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@RequestBody @Valid CreateReservationRequest request){
        ReservationResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/checkIn/{code}")
    public ResponseEntity<ReservationResponse> defineCheckIn(@PathVariable String code){
        ReservationResponse response = service.defineCheckIn(code);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/checkOut/{code}")
    public ResponseEntity<ReservationResponse> defineCheckOut(@PathVariable String code){
        ReservationResponse response = service.defineCheckOut(code);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/cancel/{code}")
    public ResponseEntity<ReservationResponse> cancel(@PathVariable String code){
        ReservationResponse response = service.cancel(code);
        return ResponseEntity.ok(response);
    }

}
