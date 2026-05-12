package com.hotel.booking.api.domain.roomType.controller;

import com.hotel.booking.api.domain.roomType.entity.RoomType;
import com.hotel.booking.api.domain.roomType.service.RoomTypeService;
import com.hotel.booking.api.domain.roomType.web.request.CreateRoomTypeRequest;
import com.hotel.booking.api.domain.roomType.web.request.UpdateRoomTypeRequest;
import com.hotel.booking.api.domain.roomType.web.response.RoomTypeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room-types")
public class RoomTypeController {

    private final RoomTypeService service;

    @GetMapping
    public ResponseEntity<Page<RoomTypeResponse>> findAll(Pageable pageable) {
        Page<RoomTypeResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RoomTypeResponse> create(@RequestBody @Valid CreateRoomTypeRequest request){
        RoomTypeResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{code}")
    public ResponseEntity<RoomTypeResponse> update(@PathVariable String code,@RequestBody @Valid UpdateRoomTypeRequest request){
        RoomTypeResponse response = service.update(code,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<RoomTypeResponse> delete(@PathVariable String code){
        service.delete(code);
        return ResponseEntity.noContent().build();
    }

}
