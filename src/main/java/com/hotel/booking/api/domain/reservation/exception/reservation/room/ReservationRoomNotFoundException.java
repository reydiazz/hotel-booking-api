package com.hotel.booking.api.domain.reservation.exception.reservation.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationRoomNotFoundException extends BusinessException {

    public ReservationRoomNotFoundException() {
        super("Reservation detail not found", ReservationRoomErrorCode.RESERVATION_ROOM_NOT_FOUND);
    }

}
