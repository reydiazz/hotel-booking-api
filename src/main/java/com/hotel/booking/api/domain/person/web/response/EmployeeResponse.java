package com.hotel.booking.api.domain.person.web.response;

import com.hotel.booking.api.domain.hotel.web.response.HotelResponse;
import com.hotel.booking.api.domain.person.model.enums.EmployeePosition;

import java.math.BigDecimal;

public record EmployeeResponse (
        String code,
        HotelResponse hotel,
        PersonResponse person,
        EmployeePosition position,
        BigDecimal salary
) {
}
