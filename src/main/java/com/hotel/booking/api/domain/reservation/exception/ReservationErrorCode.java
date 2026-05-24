package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ReservationErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND),
    RESERVATION_CANCELLED(HttpStatus.CONFLICT);

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
