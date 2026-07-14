package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationCompletedException extends BusinessException {

    public ReservationCompletedException(String code) {
        super("Reservation %s is already completed".formatted(code), ReservationErrorCode.RESERVATION_ALREADY_COMPLETED);
    }

}
