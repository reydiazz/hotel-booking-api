package com.hotel.booking.api.domain.roomType.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomTypeCodeAlreadyExistsException extends BusinessException {

    public RoomTypeCodeAlreadyExistsException() {
        super("Room type with this code already exists",RoomTypeErrorCode.CODE_ALREADY_EXISTS);
    }
}
