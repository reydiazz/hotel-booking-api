package com.hotel.booking.api.domain.reservation.service;

import com.hotel.booking.api.domain.auth.service.AuthService;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.service.CustomerService;
import com.hotel.booking.api.domain.reservation.component.ReservationMapper;
import com.hotel.booking.api.domain.reservation.exception.ReservationNotFoundException;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.model.enums.ReservationStatus;
import com.hotel.booking.api.domain.reservation.repository.ReservationRepository;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRequest;
import com.hotel.booking.api.domain.reservation.web.response.ReservationResponse;
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
    private final CustomerService customerService;
    private final ReservationMapper mapper;

    private final AuthService authService;
    private final ReservationRoomService reservationRoomService;

    @Transactional(readOnly = true)
    public Page<ReservationResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Transactional
    public ReservationResponse create(CreateReservationRequest request){
        Customer customer = customerService.findByCodeOrThrow(request.customerCode());

        Reservation reservation = new Reservation(
                CodeGenerator.next(PREFIX),
                customer,
                authService.getAuthenticatedUser()
        );
        Reservation saved = repository.save(reservation);
        reservationRoomService.create(
                saved,
                request.room()
        );
        return mapper.toResponse(saved);
    }

    @Transactional
    public ReservationResponse defineCheckIn(String code){
        Reservation reservation = findByCodeOrThrow(code);
        reservation.updateStatus(ReservationStatus.ACTIVE);
        validateStatus(reservation);
        reservation.defineCheckIn();
        return mapper.toResponse(reservation);
    }

    @Transactional
    public ReservationResponse defineCheckOut(String code){
        Reservation reservation = findByCodeOrThrow(code);
        validateStatus(reservation);
        reservation.updateStatus(ReservationStatus.DONE);
        reservation.defineCheckOut();
        reservationRoomService.done(reservation);
        return mapper.toResponse(reservation);
    }

    @Transactional
    public ReservationResponse cancel(String code){
        Reservation reservation = findByCodeOrThrow(code);
        reservation.updateStatus(ReservationStatus.CANCELLED);
        reservationRoomService.cancel(reservation);
        return mapper.toResponse(reservation);
    }

    public Reservation findByCodeOrThrow(String code){
        return repository.findById(code).orElseThrow(
                () -> new ReservationNotFoundException(code)
        );
    }

    public void validateStatus(Reservation reservation){
        if (reservation.getStatus() == ReservationStatus.CANCELLED){
            throw new ReservationNotFoundException(reservation.getCode());
        }
    }

}
