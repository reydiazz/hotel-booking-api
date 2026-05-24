package com.hotel.booking.api.domain.reservation.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class ReservationRoomDirtyException extends BusinessException {
    public ReservationRoomDirtyException(String code) {
        super("Room with code " + code + " is dirty", ReservationRoomErrorCode.ROOM_STATUS_DIRTY);
    }
}
