package com.hotel.booking.api.domain.hotel.service;

import com.hotel.booking.api.domain.hotel.exception.roomtype.RoomTypeNotFoundException;
import com.hotel.booking.api.domain.hotel.model.entity.RoomType;
import com.hotel.booking.api.domain.hotel.repository.RoomTypeRepository;
import com.hotel.booking.api.domain.hotel.web.request.CreateRoomTypeRequest;
import com.hotel.booking.api.domain.hotel.web.request.UpdateRoomTypeRequest;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomTypeService {

    public final static String PREFIX = "RTE";
    private final RoomTypeRepository repository;

    @Transactional(readOnly = true)
    public Page<RoomType> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public RoomType create(CreateRoomTypeRequest request) {
        String code = CodeGenerator.next(PREFIX);
        RoomType type = new RoomType(code, request.name(), request.description(), request.capacity(), request.basePrice());
        return repository.save(type);
    }

    @Transactional
    public RoomType update(String code, UpdateRoomTypeRequest request) {
        RoomType type = findByCodeOrThrow(code);
        type.update(request.name(), request.description(), request.capacity(), request.basePrice());
        return type;
    }

    @Transactional
    public void delete(String code) {
        RoomType type = findByCodeOrThrow(code);
        repository.delete(type);
    }

    public RoomType findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new RoomTypeNotFoundException(code)
        );
    }

}
