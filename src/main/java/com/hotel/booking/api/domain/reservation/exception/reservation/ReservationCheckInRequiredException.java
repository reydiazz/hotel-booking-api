package com.hotel.booking.api.domain.reservation.exception.reservation;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationCheckInRequiredException extends BusinessException {

    public ReservationCheckInRequiredException(String code) {
        super("Reservation %s must complete check-in before check-out".formatted(code), ReservationErrorCode.RESERVATION_CHECKED_IN_REQUIRED);
    }

}
