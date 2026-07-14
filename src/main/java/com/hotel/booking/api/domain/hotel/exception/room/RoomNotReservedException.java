package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomNotReservedException extends BusinessException {

    public RoomNotReservedException() {
        super("Room cannot be occupied because it is not reserved", RoomErrorCode.ROOM_NOT_RESERVED);
    }

}
