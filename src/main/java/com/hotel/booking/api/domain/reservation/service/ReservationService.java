package com.hotel.booking.api.domain.reservation.service;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.service.AuthService;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.service.CustomerService;
import com.hotel.booking.api.domain.reservation.component.ReservationMapper;
import com.hotel.booking.api.domain.reservation.exception.reservation.ReservationNotFoundException;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.repository.ReservationRepository;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRequest;
import com.hotel.booking.api.domain.reservation.web.response.ReservationResponse;
import com.hotel.booking.api.domain.reservation.web.response.ReservationRoomResponse;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    public static final String PREFIX = "RSV";
    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    private final AuthService authService;
    private final CustomerService customerService;
    private final ReservationRoomService reservationRoomService;

    @Transactional(readOnly = true)
    public Page<ReservationResponse> findAll(Pageable pageable) {
        Page<Reservation> page = repository.findAll(pageable);
        return page.map(mapper::toResponse);
    }

    @Transactional(readOnly = true)
    public ReservationRoomResponse findDetail(String code) {
        Reservation reservation = findByCodeOrThrow(code);
        return reservationRoomService.findByReservation(reservation);
    }

    @Transactional
    public ReservationResponse create(CreateReservationRequest request) {
        Customer customer = customerService.findByCodeOrThrow(request.customerCode());
        String code = CodeGenerator.next(PREFIX);
        User user = authService.getAuthenticatedUser();
        Reservation reservation = new Reservation(code, customer, user);
        Reservation saved = repository.save(reservation);
        reservationRoomService.create(saved, request.room());
        return mapper.toResponse(saved);
    }

    @Transactional
    public ReservationResponse checkIn(String code) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.checkIn();
        reservationRoomService.occupy(reservation);
        return mapper.toResponse(reservation);
    }

    @Transactional
    public ReservationResponse checkOut(String code) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.checkOut();
        reservationRoomService.done(reservation);
        return mapper.toResponse(reservation);
    }

    @Transactional
    public ReservationResponse cancel(String code) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.cancel();
        reservationRoomService.cancel(reservation);
        return mapper.toResponse(reservation);
    }

    public Reservation findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new ReservationNotFoundException(code)
        );
    }

}
