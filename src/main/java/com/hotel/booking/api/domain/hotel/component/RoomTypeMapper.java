package com.hotel.booking.api.domain.hotel.component;

import com.hotel.booking.api.domain.hotel.model.entity.RoomType;
import com.hotel.booking.api.domain.hotel.web.response.RoomTypeResponse;
import org.springframework.stereotype.Component;

@Component
public class RoomTypeMapper {

    public RoomTypeResponse toResponse(RoomType type) {
        return new RoomTypeResponse(
                type.getCode(),
                type.getHotel().getCode(),
                type.getName(),
                type.getDescription(),
                type.getCapacity(),
                type.getBasePrice()
        );
    }

}
