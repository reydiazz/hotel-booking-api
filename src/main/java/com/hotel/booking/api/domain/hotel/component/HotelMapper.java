package com.hotel.booking.api.domain.hotel.component;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.hotel.web.response.HotelResponse;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public HotelResponse toResponse(Hotel hotel) {
        return new HotelResponse(
                hotel.getCode(),
                hotel.getName(),
                hotel.getAddress(),
                hotel.getCity(),
                hotel.getCountry(),
                hotel.getPhone()
        );
    }

}
