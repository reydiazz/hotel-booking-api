package com.hotel.booking.api.domain.roomType.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomTypeHasRelationsException extends BusinessException {
    public RoomTypeHasRelationsException() {
        super("Room type cannot be deleted because it is associated with other entities",RoomTypeErrorCode.HAS_RELATIONS);
    }
}
