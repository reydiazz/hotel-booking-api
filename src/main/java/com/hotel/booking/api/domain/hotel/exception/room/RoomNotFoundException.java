package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomNotFoundException extends BusinessException {

    public RoomNotFoundException(String code) {
        super("Room with code '%s' not found".formatted(code), RoomErrorCode.ROOM_NOT_FOUND);
    }

}
