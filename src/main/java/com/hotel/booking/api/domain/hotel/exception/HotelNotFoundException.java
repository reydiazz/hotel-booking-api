package com.hotel.booking.api.domain.hotel.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class HotelNotFoundException extends BusinessException {

    public HotelNotFoundException(String code) {
        super("Hotel with code " + code +" not found", HotelErrorCode.NOT_FOUND);
    }

}
