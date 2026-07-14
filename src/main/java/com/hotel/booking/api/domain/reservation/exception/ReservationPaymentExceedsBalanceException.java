package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationPaymentExceedsBalanceException extends BusinessException {

    public ReservationPaymentExceedsBalanceException() {
        super("The payment amount exceeds the remaining reservation balance.", ReservationErrorCode.RESERVATION_PAYMENT_EXCEEDED);
    }

}
