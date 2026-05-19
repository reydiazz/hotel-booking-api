package com.hotel.booking.api.domain.person.exception.person;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum PersonErrorCode implements ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;

    PersonErrorCode(HttpStatus httpStatus) {
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
