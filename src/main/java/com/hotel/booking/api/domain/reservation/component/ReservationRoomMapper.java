package com.hotel.booking.api.domain.reservation.component;

import com.hotel.booking.api.domain.reservation.model.entity.ReservationRoom;
import com.hotel.booking.api.domain.reservation.web.response.ReservationRoomResponse;
import org.springframework.stereotype.Component;

@Component
public class ReservationRoomMapper {

    public ReservationRoomResponse toResponse(ReservationRoom reservationRoom) {
        return new ReservationRoomResponse(
                reservationRoom.getCode(),
                reservationRoom.getRoom().getNumber(),
                reservationRoom.getRoom().getFloor(),
                reservationRoom.getRoom().getType().getName(),
                reservationRoom.getPricePerNight(),
                reservationRoom.getNights()
        );
    }

}
