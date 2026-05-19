package com.hotel.booking.api.domain.person.exception.employee;

import com.hotel.booking.api.shared.exception.BusinessException;

public class EmployeeNotFoundException extends BusinessException {

    public EmployeeNotFoundException(String code) {
        super("Employee with code " + code + " not found", EmployeeErrorCode.NOT_FOUND);
    }

}
