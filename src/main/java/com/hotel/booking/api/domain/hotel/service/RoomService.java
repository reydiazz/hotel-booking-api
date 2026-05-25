package com.hotel.booking.api.domain.hotel.service;

import com.hotel.booking.api.domain.hotel.exception.room.RoomNotFoundException;
import com.hotel.booking.api.domain.hotel.component.RoomMapper;
import com.hotel.booking.api.domain.hotel.exception.room.RoomNumberAlreadyExistsException;
import com.hotel.booking.api.domain.hotel.model.entity.Room;
import com.hotel.booking.api.domain.hotel.repository.RoomRepository;
import com.hotel.booking.api.domain.hotel.web.request.CreateRoomRequest;
import com.hotel.booking.api.domain.hotel.web.request.UpdateRoomRequest;
import com.hotel.booking.api.domain.hotel.web.response.RoomResponse;
import com.hotel.booking.api.domain.hotel.model.entity.RoomType;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    public static final String PREFIX = "ROM";
    private final RoomMapper mapper;
    private final RoomRepository repository;

    private final RoomTypeService typeService;

    @Transactional(readOnly = true)
    public Page<RoomResponse> findAll(Pageable pageable) {
        Page<Room> page = repository.findAll(pageable);
        return page.map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public RoomResponse findByCode(String code) {
        Room room = findByCodeOrThrow(code);
        return mapper.toResponse(room);
    }

    @Transactional
    public RoomResponse create(CreateRoomRequest request) {
        verifyNumber(request.number());
        String code = CodeGenerator.next(PREFIX);
        RoomType type = typeService.findByCodeOrThrow(request.typeCode());
        Room room = new Room(code, type, request.number(), request.floor());
        Room saved = repository.save(room);
        return mapper.toResponse(saved);
    }

    @Transactional
    public RoomResponse update(String code, UpdateRoomRequest request) {
        verifyNumber(request.number(), code);
        Room room = findByCodeOrThrow(code);
        RoomType type = typeService.findByCodeOrThrow(request.typeCode());
        room.update(type, request.number(), request.floor());
        return mapper.toResponse(room);
    }

    @Transactional
    public RoomResponse clean(String code) {
        Room room = findByCodeOrThrow(code);
        room.clean();
        return mapper.toResponse(room);
    }

    @Transactional
    public RoomResponse markOutOfService(String code) {
        Room room = findByCodeOrThrow(code);
        room.sendOutOfService();
        return mapper.toResponse(room);
    }

    @Transactional
    public void delete(String code) {
        Room room = findByCodeOrThrow(code);
        repository.delete(room);
    }

    public Room findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new RoomNotFoundException(code)
        );
    }

    private void verifyNumber(Integer number) {
        if (repository.existsByNumber(number)) {
            throw new RoomNumberAlreadyExistsException(number);
        }
    }

    private void verifyNumber(Integer number, String code) {
        if (repository.existsByNumberAndCodeNot(number, code)) {
            throw new RoomNumberAlreadyExistsException(number);
        }
    }

}
