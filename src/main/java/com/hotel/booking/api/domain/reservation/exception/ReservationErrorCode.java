package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ReservationErrorCode implements ErrorCode {

    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND),
    RESERVATION_CANCELLED(HttpStatus.CONFLICT),
    RESERVATION_ALREADY_CHECKED_IN(HttpStatus.CONFLICT),
    RESERVATION_ALREADY_CHECKED_OUT(HttpStatus.CONFLICT),
    RESERVATION_ALREADY_COMPLETED(HttpStatus.CONFLICT),
    RESERVATION_CHECKED_IN_REQUIRED(HttpStatus.CONFLICT),
    RESERVATION_PAYMENT_EXCEEDED(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    ReservationErrorCode(HttpStatus httpStatus) {
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
