package com.hotel.booking.api.domain.person.web.request;

import com.hotel.booking.api.domain.hotel.web.request.CreateHotelRequest;
import com.hotel.booking.api.domain.person.model.enums.EmployeePosition;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateEmployeeRequest(
        @NotNull(message = "Employee hotel is required")
        CreateHotelRequest hotel,
        @NotNull(message = "Employee data is required")
        CreatePersonRequest person,
        @NotNull(message = "Employee position not be empty")
        EmployeePosition position,
        @NotNull(message = "Employee salary not be empty")
        @DecimalMin(value = "0.01", message = "Employee salary  must be greater than zero")
        BigDecimal salary
) {
}
