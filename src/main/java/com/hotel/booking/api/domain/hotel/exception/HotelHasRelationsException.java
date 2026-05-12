package com.hotel.booking.api.domain.hotel.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class HotelHasRelationsException extends BusinessException {

    public HotelHasRelationsException() {
        super("Hotel cannot be deleted because it is associated with other entities", HotelErrorCode.HAS_RELATIONS);
    }
}
