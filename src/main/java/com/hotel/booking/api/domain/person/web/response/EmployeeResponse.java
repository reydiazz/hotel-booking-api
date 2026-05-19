package com.hotel.booking.api.domain.person.web.response;

import com.hotel.booking.api.domain.person.model.enums.EmployeePosition;

import java.math.BigDecimal;

public record EmployeeResponse(
        String code,
        String hotelCode,
        PersonResponse person,
        EmployeePosition position,
        BigDecimal salary
) {
}
