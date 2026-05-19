package com.hotel.booking.api.domain.person.exception;

import com.hotel.booking.api.shared.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum EmployeeErrorCode implements ErrorCode {

    CODE_ALREADY_EXISTS(HttpStatus.CONFLICT),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    HAS_RELATIONS(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;

     EmployeeErrorCode(HttpStatus httpStatus){
        this.httpStatus = httpStatus;

    }

    @Override
    public String getCode() {
        return name ();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
