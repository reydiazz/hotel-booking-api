package com.hotel.booking.api.domain.reservation.component;

import com.hotel.booking.api.domain.reservation.model.entity.ReservationRoom;
import com.hotel.booking.api.domain.reservation.web.response.ReservationRoomResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationRoomMapper {

    public ReservationRoomResponse toResponse(ReservationRoom reservationRoom) {
        return new ReservationRoomResponse(
                reservationRoom.getCode(),
                reservationRoom.getRoom().getNumber(),
                reservationRoom.getRoom().getFloor(),
                reservationRoom.getRoom().getType().getName(),
                reservationRoom.getPricePerNight(),
                reservationRoom.getCheckIn(),
                reservationRoom.getCheckOut(),
                reservationRoom.getNights()
        );
    }

    public List<ReservationRoomResponse> toResponseList(List<ReservationRoom> rooms) {
        List<ReservationRoomResponse> responseList = new ArrayList<>();
        for (ReservationRoom reservationRoom : rooms) {
            responseList.add(toResponse(reservationRoom));
        }
        return responseList;
    }

}
