package com.hotel.booking.api.shared.exception;

public class BusinessException extends RuntimeException {

    private final ErrorCode code;

    public BusinessException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code.getCode();
    }

}
