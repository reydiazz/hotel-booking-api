package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class PaymentExceedsRemainingBalanceException extends BusinessException {
    public PaymentExceedsRemainingBalanceException() {
        super("The payment amount exceeds the remaining balance", PaymentErrorCode.PAYMENT_EXCEEDS_REMAIN_BALANCE);
    }
}
