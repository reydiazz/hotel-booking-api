package com.hotel.booking.api.domain.roomtype.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomTypeNotFoundException extends BusinessException {

    public RoomTypeNotFoundException(String code) {
        super("Room type with code " + code +" not found", RoomTypeErrorCode.NOT_FOUND);
    }

}
