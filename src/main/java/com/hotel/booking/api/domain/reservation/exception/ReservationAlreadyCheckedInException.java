package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationAlreadyCheckedInException extends BusinessException {

    public ReservationAlreadyCheckedInException(String code) {
        super("Reservation %s has already checked in".formatted(code), ReservationErrorCode.RESERVATION_ALREADY_CHECKED_IN);
    }

}
