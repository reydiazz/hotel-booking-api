package com.hotel.booking.api.domain.reservation.component;

import com.hotel.booking.api.domain.person.component.CustomerMapper;
import com.hotel.booking.api.domain.person.model.entity.Customer;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.web.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final CustomerMapper customerMapper;

    public ReservationResponse toResponse(Reservation reservation){
        return new ReservationResponse(
                reservation.getCode(),
                customerMapper.toResponse(reservation.getCustomer()),
                reservation.getUser().getUsername(),
                reservation.getUser().getEmployee().getPerson().getFirstName(),
                reservation.getStatus(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getCreatedAt()
        );
    }
}
