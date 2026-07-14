package com.hotel.booking.api.domain.hotel.exception.roomtype;

import com.hotel.booking.api.shared.exception.BusinessException;

public class DuplicateRoomTypeNameException extends BusinessException {

    public DuplicateRoomTypeNameException(String name) {
        super("Room type with name '" + name + "' already exists", RoomTypeErrorCode.NAME_ROOM_TYPE_DUPLICATED);
    }

}
