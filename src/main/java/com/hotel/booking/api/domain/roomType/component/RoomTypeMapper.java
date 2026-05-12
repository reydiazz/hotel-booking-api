package com.hotel.booking.api.domain.roomType.component;

import com.hotel.booking.api.domain.roomType.entity.RoomType;
import com.hotel.booking.api.domain.roomType.web.response.RoomTypeResponse;
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
