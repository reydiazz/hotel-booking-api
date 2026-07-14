package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationRoomNotFoundException extends BusinessException {

    public ReservationRoomNotFoundException(String code) {
        super("Reservation with code '%s' not found".formatted(code), ReservationErrorCode.RESERVATION_ROOM_NOT_FOUND);
    }
}
