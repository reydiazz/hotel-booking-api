package com.hotel.booking.api.domain.room.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomHasRelationsException extends BusinessException {

    public RoomHasRelationsException() {
        super("Room cannot be deleted because it is associated with other entities", RoomErrorCode.HAS_RELATIONS);
    }

}
