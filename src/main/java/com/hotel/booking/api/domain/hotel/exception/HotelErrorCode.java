package com.hotel.booking.api.domain.hotel.exception;

import com.hotel.booking.api.shared.exception.ErrorCode;

public enum HotelErrorCode implements ErrorCode {

    CODE_ALREADY_EXISTS,
    NOT_FOUND,
    HAS_RELATIONS;

    @Override
    public String getCode() {
        return name();
    }

}
