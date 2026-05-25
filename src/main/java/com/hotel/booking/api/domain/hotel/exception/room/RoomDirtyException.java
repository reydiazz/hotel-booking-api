package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomDirtyException extends BusinessException {

    public RoomDirtyException(String code) {
        super("Room with code '%s' is dirty".formatted(code), RoomErrorCode.ROOM_STATUS_DIRTY);
    }

}
