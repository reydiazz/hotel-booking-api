package com.hotel.booking.api.domain.reservation.service;

import com.hotel.booking.api.domain.auth.service.AuthService;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.person.service.CustomerService;
import com.hotel.booking.api.domain.reservation.component.ReservationMapper;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.repository.ReservationRepository;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRequest;
import com.hotel.booking.api.domain.reservation.web.response.ReservationResponse;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public ReservationResponse create(CreateReservationRequest request){
        Customer customer = customerService.findByCodeOrThrow(request.customerCode());
        Reservation reservation = new Reservation(
                CodeGenerator.next(PREFIX),
                customer,
                authService.getAuthenticatedUser()
        );
        Reservation saved = repository.save(reservation);
        return mapper.toResponse(saved);
    }
}
