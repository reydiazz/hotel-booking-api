package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomOutOfServiceException extends BusinessException {

    public RoomOutOfServiceException(String code) {
        super("Room with code '%s' is out of service".formatted(code), RoomErrorCode.ROOM_STATUS_OUT_OF_SERVICE);
    }

}
