package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum RoomErrorCode implements ErrorCode {

    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND),
    ROOM_STATUS_OCCUPIED(HttpStatus.CONFLICT),
    ROOM_STATUS_DIRTY(HttpStatus.CONFLICT),
    ROOM_STATUS_OUT_OF_SERVICE(HttpStatus.CONFLICT),
    ROOM_STATUS_RESERVED(HttpStatus.CONFLICT),
    ROOM_NUMBER_ALREADY_EXISTS(HttpStatus.CONFLICT);

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
