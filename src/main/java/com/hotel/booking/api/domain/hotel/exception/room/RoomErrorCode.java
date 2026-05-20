package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum RoomErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND);

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
