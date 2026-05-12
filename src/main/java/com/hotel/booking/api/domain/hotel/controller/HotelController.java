package com.hotel.booking.api.domain.hotel.controller;

import com.hotel.booking.api.domain.hotel.service.HotelService;
import com.hotel.booking.api.domain.hotel.web.request.CreateHotelRequest;
import com.hotel.booking.api.domain.hotel.web.request.UpdateHotelRequest;
import com.hotel.booking.api.domain.hotel.web.response.HotelResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService service;

    @GetMapping
    public ResponseEntity<Page<HotelResponse>> findAll(Pageable pageable) {
        Page<HotelResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<HotelResponse> findByCode(@PathVariable String code) {
        HotelResponse response = service.findByCode(code);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<HotelResponse> create(@RequestBody @Valid CreateHotelRequest request) {
        HotelResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{code}")
    public ResponseEntity<HotelResponse> update(@PathVariable String code, @RequestBody @Valid UpdateHotelRequest request) {
        HotelResponse response = service.update(code, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<HotelResponse> delete(@PathVariable String code) {
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

}
