package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ReservationRoomErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND),
    ROOM_STATUS_OCCUPIED(HttpStatus.CONFLICT),
    ROOM_STATUS_DIRTY(HttpStatus.CONFLICT),
    ROOM_OUT_OF_SERVICE(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    ReservationRoomErrorCode(HttpStatus httpStatus) {
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
