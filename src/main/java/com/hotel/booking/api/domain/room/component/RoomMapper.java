package com.hotel.booking.api.domain.room.component;

import com.hotel.booking.api.domain.room.model.entity.Room;
import com.hotel.booking.api.domain.room.web.response.RoomResponse;
import com.hotel.booking.api.domain.roomtype.component.RoomTypeMapper;
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
                typeMapper.toResponse(room.getType()),
                room.getNumber(),
                room.getFloor(),
                room.getStatus(),
                room.getLastCleaned()
        );
    }

}
