package com.hotel.booking.api.domain.reservation.component;

import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.web.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final ReservationRoomMapper reservationRoomMapper;

    public ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getCode(),
                reservation.getCustomer().getCode(),
                reservation.getCustomer().getPerson().getFullName(),
                reservation.getUser().getCode(),
                reservation.getUser().getPerson().getFullName(),
                reservation.getStatus(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getCreatedAt(),
                reservationRoomMapper.toResponseList(reservation.getRooms())
        );
    }

}
