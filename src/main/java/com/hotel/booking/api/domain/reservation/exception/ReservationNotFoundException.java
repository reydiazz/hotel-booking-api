package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationNotFoundException extends BusinessException {

    public ReservationNotFoundException(String code) {
        super("Reservation with code " + code + " not found", ReservationErrorCode.NOT_FOUND);
    }

}
