package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomNotOutOfServiceException extends BusinessException {

    public RoomNotOutOfServiceException() {
        super("Room cannot be released because it is not out of service",RoomErrorCode.ROOM_NOT_OUT_OF_SERVICE);
    }

}
