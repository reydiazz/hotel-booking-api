package com.hotel.booking.api.domain.hotel.exception.room;

import com.hotel.booking.api.shared.exception.BusinessException;

public class RoomNumberAlreadyExistsException extends BusinessException {

    public RoomNumberAlreadyExistsException(Integer number) {
        super("Room with number '%s' already exists".formatted(number), RoomErrorCode.ROOM_NUMBER_ALREADY_EXISTS);
    }

}
