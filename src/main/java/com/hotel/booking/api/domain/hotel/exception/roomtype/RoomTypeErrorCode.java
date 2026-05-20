package com.hotel.booking.api.domain.hotel.exception.roomtype;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum RoomTypeErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;

    RoomTypeErrorCode(HttpStatus httpStatus) {
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
