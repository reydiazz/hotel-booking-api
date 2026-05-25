package com.hotel.booking.api.domain.reservation.exception.reservation;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationNotFoundException extends BusinessException {

    public ReservationNotFoundException(String code) {
        super("Reservation with code '%s' not found".formatted(code), ReservationErrorCode.RESERVATION_NOT_FOUND);
    }

}
