package com.hotel.booking.api.domain.hotel.component;

import com.hotel.booking.api.domain.hotel.model.entity.Room;
import com.hotel.booking.api.domain.hotel.web.response.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomMapper {

    private final RoomTypeMapper typeMapper;

    public RoomResponse toResponse(Room room) {
        return new RoomResponse(
                room.getCode(),
                room.getHotel().getCode(),
                room.getType().getCode(),
                room.getType().getName(),
                room.getNumber(),
                room.getFloor(),
                room.getStatus(),
                room.getLastCleaned()
        );
    }

}
