package com.hotel.booking.api.domain.reservation.web.controller;

import com.hotel.booking.api.domain.reservation.component.ReservationMapper;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.service.ReservationService;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRequest;
import com.hotel.booking.api.domain.reservation.web.response.ReservationResponse;
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
    private final ReservationMapper mapper;

    @GetMapping
    public ResponseEntity<Page<ReservationResponse>> findAll(Pageable pageable) {
        Page<Reservation> page = service.findAll(pageable);
        return ResponseEntity.ok(page.map(mapper::toResponse));
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@RequestBody @Valid CreateReservationRequest request) {
        Reservation reservation = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(reservation));
    }

    @PatchMapping("/{code}/rooms/{roomCode}/check-in")
    public ResponseEntity<ReservationResponse> checkIn(@PathVariable String code, @PathVariable String roomCode) {
        Reservation reservation = service.checkIn(code, roomCode);
        return ResponseEntity.ok(mapper.toResponse(reservation));
    }

    @PatchMapping("/{code}/rooms/{roomCode}/check-out")
    public ResponseEntity<ReservationResponse> checkOut(@PathVariable String code, @PathVariable String roomCode) {
        Reservation reservation = service.checkOut(code, roomCode);
        return ResponseEntity.ok(mapper.toResponse(reservation));
    }

    @PatchMapping("/{code}/cancel")
    public ResponseEntity<ReservationResponse> cancel(@PathVariable String code) {
        Reservation reservation = service.cancel(code);
        return ResponseEntity.ok(mapper.toResponse(reservation));
    }

}
