package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomNotAvailableException extends BusinessException {

    public RoomNotAvailableException() {
        super("Room operation cannot be performed because the room is not available", RoomErrorCode.ROOM_NOT_AVAILABLE);
    }

}
