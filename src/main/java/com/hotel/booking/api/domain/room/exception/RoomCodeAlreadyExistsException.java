package com.hotel.booking.api.domain.room.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomCodeAlreadyExistsException extends BusinessException {

    public RoomCodeAlreadyExistsException() {
        super("Room with this code already exists", RoomErrorCode.CODE_ALREADY_EXISTS);
    }

}
