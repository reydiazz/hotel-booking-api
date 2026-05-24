package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationRoomOutOfServiceException extends BusinessException {
    public ReservationRoomOutOfServiceException(String code) {
        super("Room with code " + code + " is out of service", ReservationRoomErrorCode.ROOM_OUT_OF_SERVICE);
    }
}
