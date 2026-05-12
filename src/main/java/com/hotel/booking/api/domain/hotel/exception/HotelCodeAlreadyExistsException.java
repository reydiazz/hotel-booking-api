package com.hotel.booking.api.domain.hotel.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class HotelCodeAlreadyExistsException extends BusinessException {

    public HotelCodeAlreadyExistsException() {
        super("Hotel with this code already exists", HotelErrorCode.CODE_ALREADY_EXISTS);
    }

}
