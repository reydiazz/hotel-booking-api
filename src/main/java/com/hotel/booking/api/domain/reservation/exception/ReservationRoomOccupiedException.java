package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationRoomOccupiedException extends BusinessException {

    public ReservationRoomOccupiedException(String code) {
        super("Room with code " + code + " is already occupied", ReservationRoomErrorCode.ROOM_STATUS_OCCUPIED);
    }

}
