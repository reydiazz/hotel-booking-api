package com.hotel.booking.api.domain.roomtype.component;

import com.hotel.booking.api.domain.roomtype.entity.RoomType;
import com.hotel.booking.api.domain.roomtype.web.response.RoomTypeResponse;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeMapper {

    public RoomTypeResponse toResponse(RoomType roomType) {
        return new RoomTypeResponse(
                roomType.getCode(),
                roomType.getHotel().getCode(),
                roomType.getName(),
                roomType.getDescription(),
                roomType.getCapacity(),
                roomType.getBasePrice()
        );
    }
}
