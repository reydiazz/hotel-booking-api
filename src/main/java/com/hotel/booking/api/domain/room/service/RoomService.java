package com.hotel.booking.api.domain.room.service;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.hotel.service.HotelService;
import com.hotel.booking.api.domain.room.component.RoomMapper;
import com.hotel.booking.api.domain.room.exception.RoomCodeAlreadyExistsException;
import com.hotel.booking.api.domain.room.exception.RoomHasRelationsException;
import com.hotel.booking.api.domain.room.exception.RoomNotFoundException;
import com.hotel.booking.api.domain.room.model.entity.Room;
import com.hotel.booking.api.domain.room.repository.RoomRepository;
import com.hotel.booking.api.domain.room.web.request.CreateRoomRequest;
import com.hotel.booking.api.domain.room.web.request.UpdateRoomRequest;
import com.hotel.booking.api.domain.room.web.request.UpdateRoomStatusRequest;
import com.hotel.booking.api.domain.room.web.response.RoomResponse;
import com.hotel.booking.api.domain.roomtype.entity.RoomType;
import com.hotel.booking.api.domain.roomtype.service.RoomTypeService;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomMapper mapper;
    private final RoomRepository repository;
    private final HotelService hotelService;
    private final RoomTypeService typeService;
    public static final String PREFIX = "ROM";

    @Transactional(readOnly = true)
    public Page<RoomResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public RoomResponse findByCode(String code) {
        Room room = findByCodeOrThrow(code);
        return mapper.toResponse(room);
    }

    @Transactional(readOnly = true)
    public Page<RoomResponse> findByHotelCode(String hotelCode, Pageable pageable) {
        Hotel hotel = hotelService.findByCodeOrThrow(hotelCode);
        return repository.findByHotel(hotel, pageable).map(mapper::toResponse);
    }

    @Transactional
    public RoomResponse create(CreateRoomRequest request) {
        String code = CodeGenerator.next(PREFIX);
        Hotel hotel = hotelService.findByCodeOrThrow(request.hotelCode());
        RoomType type = typeService.findByCodeOrThrow(request.typeCode());
        Room room = new Room(
                code,
                hotel,
                type,
                request.number(),
                request.floor()
        );
        try {
            Room saved = repository.save(room);
            return mapper.toResponse(saved);
        } catch (DataIntegrityViolationException e) {
            throw new RoomCodeAlreadyExistsException();
        }
    }

    @Transactional
    public RoomResponse updateByCode(String code, UpdateRoomRequest request) {
        Room room = findByCodeOrThrow(code);
        RoomType type = typeService.findByCodeOrThrow(request.typeCode());
        room.update(
                type,
                request.number(),
                request.floor()
        );
        return mapper.toResponse(room);
    }

    @Transactional
    public RoomResponse updateRoomStatus(String code, UpdateRoomStatusRequest request) {
        Room room = findByCodeOrThrow(code);
        room.updateStatus(request.status());
        return mapper.toResponse(room);
    }

    @Transactional
    public RoomResponse updateLastCleaned(String code) {
        Room room = findByCodeOrThrow(code);
        room.updateLastCleaned(LocalDateTime.now());
        return mapper.toResponse(room);
    }

    @Transactional
    public void deleteByCode(String code) {
        Room room = findByCodeOrThrow(code);
        try {
            repository.delete(room);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RoomHasRelationsException();
        }
    }

    public Room findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new RoomNotFoundException(code)
        );
    }

}
