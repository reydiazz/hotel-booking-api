package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomNotDirtyException extends BusinessException {

    public RoomNotDirtyException() {
        super("Room cannot be cleaned because it is not dirty", RoomErrorCode.ROOM_NOT_DIRTY);
    }

}
