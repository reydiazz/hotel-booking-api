package com.hotel.booking.api.domain.person.exception;

import com.hotel.booking.api.shared.exception.BusinessException;

public class EmployeeCodeAlreadyException extends BusinessException {
    public EmployeeCodeAlreadyException() {
        super("Employee with this code already exists", EmployeeErrorCode.CODE_ALREADY_EXISTS);
    }
}
