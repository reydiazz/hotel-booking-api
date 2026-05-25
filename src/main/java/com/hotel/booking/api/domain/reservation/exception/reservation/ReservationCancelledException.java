package com.hotel.booking.api.domain.reservation.exception.reservation;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationCancelledException extends BusinessException {

    public ReservationCancelledException(String message) {
        super(message, ReservationErrorCode.RESERVATION_CANCELLED);
    }

}
