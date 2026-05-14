package com.hotel.booking.api.domain.roomtype.service;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.hotel.service.HotelService;
import com.hotel.booking.api.domain.roomtype.component.RoomTypeMapper;
import com.hotel.booking.api.domain.roomtype.entity.RoomType;
import com.hotel.booking.api.domain.roomtype.exception.RoomTypeCodeAlreadyExistsException;
import com.hotel.booking.api.domain.roomtype.exception.RoomTypeHasRelationsException;
import com.hotel.booking.api.domain.roomtype.exception.RoomTypeNotFoundException;
import com.hotel.booking.api.domain.roomtype.repository.RoomTypeRepository;
import com.hotel.booking.api.domain.roomtype.web.request.CreateRoomTypeRequest;
import com.hotel.booking.api.domain.roomtype.web.request.UpdateRoomTypeRequest;
import com.hotel.booking.api.domain.roomtype.web.response.RoomTypeResponse;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomTypeService {

    private final HotelService hotelService;
    private final RoomTypeRepository repository;
    private final RoomTypeMapper mapper;
    public final static String PREFIX = "RTE";

    @Transactional(readOnly = true)
    public Page<RoomTypeResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<RoomTypeResponse> findByHotelCode(String hotelCode, Pageable pageable) {
        Hotel hotel = hotelService.findByCodeOrThrow(hotelCode);
        return repository.findByHotel(hotel, pageable).map(mapper::toResponse);
    }

    @Transactional
    public RoomTypeResponse create(CreateRoomTypeRequest request) {
        Hotel hotel = hotelService.findByCodeOrThrow(request.hotelCode());
        String code = CodeGenerator.next(PREFIX);
        RoomType type = new RoomType(
                code,
                hotel,
                request.name(),
                request.description(),
                request.capacity(),
                request.basePrice()
        );
        try {
            RoomType saved = repository.save(type);
            return mapper.toResponse(saved);
        } catch (DataIntegrityViolationException e) {
            throw new RoomTypeCodeAlreadyExistsException();
        }
    }

    @Transactional
    public RoomTypeResponse updateByCode(String code, UpdateRoomTypeRequest request) {
        RoomType type = findByCodeOrThrow(code);
        type.update(
                request.name(),
                request.description(),
                request.capacity(),
                request.basePrice()
        );
        return mapper.toResponse(type);
    }

    @Transactional
    public void deleteByCode(String code) {
        RoomType type = findByCodeOrThrow(code);
        try {
            repository.delete(type);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new RoomTypeHasRelationsException();
        }
    }

    public RoomType findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new RoomTypeNotFoundException(code)
        );
    }

}
