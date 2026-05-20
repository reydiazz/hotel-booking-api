package com.hotel.booking.api.domain.hotel.service;

import com.hotel.booking.api.domain.hotel.component.HotelMapper;
import com.hotel.booking.api.domain.hotel.exception.hotel.HotelNotFoundException;
import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.hotel.repository.HotelRepository;
import com.hotel.booking.api.domain.hotel.web.request.CreateHotelRequest;
import com.hotel.booking.api.domain.hotel.web.request.UpdateHotelRequest;
import com.hotel.booking.api.domain.hotel.web.response.HotelResponse;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelService {

    public static final String PREFIX = "HOT";
    private final HotelRepository repository;
    private final HotelMapper mapper;

    @Transactional(readOnly = true)
    public Page<HotelResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public HotelResponse findByCode(String code) {
        Hotel hotel = findByCodeOrThrow(code);
        return mapper.toResponse(hotel);
    }

    @Transactional
    public HotelResponse create(CreateHotelRequest request) {
        String code = CodeGenerator.next(PREFIX);
        Hotel hotel = new Hotel(
                code,
                request.name(),
                request.address(),
                request.city(),
                request.country(),
                request.phone()
        );
        Hotel saved = repository.save(hotel);
        return mapper.toResponse(saved);
    }

    @Transactional
    public HotelResponse update(String code, UpdateHotelRequest request) {
        Hotel hotel = findByCodeOrThrow(code);
        hotel.update(
                request.name(),
                request.address(),
                request.city(),
                request.country(),
                request.phone()
        );
        return mapper.toResponse(hotel);
    }

    @Transactional
    public void delete(String code) {
        Hotel hotel = findByCodeOrThrow(code);
        repository.delete(hotel);
    }

    public Hotel findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new HotelNotFoundException(code)
        );
    }

}
