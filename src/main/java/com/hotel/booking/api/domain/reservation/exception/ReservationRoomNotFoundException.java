package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationRoomNotFoundException extends BusinessException {
    public ReservationRoomNotFoundException() {
        super("Reservation room not found", ReservationRoomErrorCode.NOT_FOUND);
    }
}
