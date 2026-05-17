package com.hotel.booking.api.domain.person.web.request;

import com.hotel.booking.api.domain.person.model.enums.EmployeePosition;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateEmployeeRequest(
        @NotBlank(message = "Employee hotel is required")
        String hotelCode,
        @NotNull(message = "Employee data is required")
        UpdatePersonRequest person,
        @NotNull(message = "Employee position not be empty")
        EmployeePosition positive,
        @NotNull(message = "Employee salary not be empty")
        @DecimalMin(value = "0.01", message = "Employee salary  must be greater than zero")
        BigDecimal salary
) {
}
