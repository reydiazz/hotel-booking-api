package com.hotel.booking.api.domain.reservation.exception.payment;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PaymentErrorCode implements ErrorCode {

    PAYMENT_EXCEEDS_REMAIN_BALANCE(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    PaymentErrorCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
