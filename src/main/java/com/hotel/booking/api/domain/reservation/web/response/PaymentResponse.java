package com.hotel.booking.api.domain.reservation.web.response;

import com.hotel.booking.api.domain.reservation.model.enums.PaymentMethod;
import com.hotel.booking.api.domain.reservation.model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        String code,
        String userCode,
        String userFullName,
        PaymentMethod method,
        PaymentStatus status,
        BigDecimal amount,
        LocalDateTime payment
) {
}
