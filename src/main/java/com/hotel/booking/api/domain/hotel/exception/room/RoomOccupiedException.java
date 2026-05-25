package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomOccupiedException extends BusinessException {

    public RoomOccupiedException(String code) {
        super("Room with code '%s' is already occupied".formatted(code), RoomErrorCode.ROOM_STATUS_OCCUPIED);
    }

}
