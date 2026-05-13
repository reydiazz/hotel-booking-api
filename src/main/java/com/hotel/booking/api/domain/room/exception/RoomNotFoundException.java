package com.hotel.booking.api.domain.room.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomNotFoundException extends BusinessException {

    public RoomNotFoundException(String code) {
        super("Room with code " + code +" not found", RoomErrorCode.NOT_FOUND);
    }
}
