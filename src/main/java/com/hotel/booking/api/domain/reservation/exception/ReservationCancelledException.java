package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationCancelledException extends BusinessException {

    public ReservationCancelledException() {
        super("Cancelled reservations cannot receive payments", ReservationErrorCode.RESERVATION_CANCELLED);
    }

}
