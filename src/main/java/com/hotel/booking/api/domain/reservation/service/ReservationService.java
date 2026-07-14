package com.hotel.booking.api.domain.reservation.service;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.service.AuthService;
import com.hotel.booking.api.domain.hotel.model.entity.Room;
import com.hotel.booking.api.domain.hotel.service.RoomService;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.service.CustomerService;
import com.hotel.booking.api.domain.reservation.exception.ReservationNotFoundException;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.repository.ReservationRepository;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRequest;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRoomRequest;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private static final String PREFIX_RESERVATION = "RSV";
    private static final String PREFIX_ROOM_RESERVATION = "RVR";

    private final ReservationRepository repository;

    private final AuthService authService;
    private final CustomerService customerService;
    private final RoomService roomService;

    @Transactional(readOnly = true)
    public Page<Reservation> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public Reservation create(CreateReservationRequest request) {
        Customer customer = customerService.findByCodeOrThrow(request.customerCode());
        User user = authService.getAuthenticatedUser();
        Reservation reservation = new Reservation(CodeGenerator.next(PREFIX_RESERVATION), customer, user);
        for (CreateReservationRoomRequest roomRequest : request.rooms()) {
            Room room = roomService.findByCodeOrThrow(roomRequest.roomCode());
            reservation.addRoom(CodeGenerator.next(PREFIX_ROOM_RESERVATION), room);
        }
        return repository.save(reservation);
    }

    @Transactional
    public Reservation checkIn(String code, String reservationRoomCode) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.checkIn(reservationRoomCode);
        return reservation;
    }

    @Transactional
    public Reservation checkOut(String code, String reservationRoomCode) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.checkOut(reservationRoomCode);
        return reservation;
    }

    @Transactional
    public Reservation cancel(String code) {
        Reservation reservation = findByCodeOrThrow(code);
        reservation.cancel();
        return reservation;
    }

    public Reservation findByCodeOrThrow(String code) {
        return repository.findById(code).orElseThrow(
                () -> new ReservationNotFoundException(code)
        );
    }

}