package com.hotel.booking.api.domain.hotel.exception.hotel;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum HotelErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;

    HotelErrorCode(HttpStatus httpStatus) {
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
