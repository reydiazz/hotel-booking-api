package com.hotel.booking.api.domain.reservation.service;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.service.AuthService;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.service.CustomerService;
import com.hotel.booking.api.domain.reservation.exception.reservation.ReservationNotFoundException;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.model.entity.ReservationRoom;
import com.hotel.booking.api.domain.reservation.repository.ReservationRepository;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRequest;
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

    private final AuthService authService;
    private final CustomerService customerService;
    private final ReservationRoomService reservationRoomService;

    @Transactional(readOnly = true)
    public Page<Reservation> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public Reservation create(CreateReservationRequest request) {
        Customer customer = customerService.findByCodeOrThrow(request.customerCode());
        String code = CodeGenerator.next(PREFIX);
        User user = authService.getAuthenticatedUser();
        Reservation reservation = new Reservation(code, customer, user);
        Reservation saved = repository.save(reservation);
        reservationRoomService.create(saved, request.room());
        return saved;
    }

    @Transactional
    public Reservation checkIn(String code) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.checkIn();
        reservationRoomService.occupy(reservation);
        return reservation;
    }

    @Transactional
    public Reservation checkOut(String code) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.checkOut();
        reservationRoomService.done(reservation);
        return reservation;
    }

    @Transactional
    public Reservation cancel(String code) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.cancel();
        reservationRoomService.cancel(reservation);
        return reservation;
    }

    public Reservation findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new ReservationNotFoundException(code)
        );
    }

}
