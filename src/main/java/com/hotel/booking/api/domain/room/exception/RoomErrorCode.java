package com.hotel.booking.api.domain.room.exception;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum RoomErrorCode implements ErrorCode {

    CODE_ALREADY_EXISTS(HttpStatus.CONFLICT),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    HAS_RELATIONS(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

    RoomErrorCode(HttpStatus httpStatus) {
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
