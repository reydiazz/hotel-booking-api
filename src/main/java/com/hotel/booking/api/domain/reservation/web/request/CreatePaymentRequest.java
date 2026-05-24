package com.hotel.booking.api.domain.reservation.web.request;

import com.hotel.booking.api.domain.reservation.model.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        @NotBlank(message = "Payment reservation code not be empty")
        String reservationCode,
        @NotNull(message = "Payment method not be empty")
        PaymentMethod method,
        @NotNull(message = "Payment amount not be empty")
        BigDecimal amount
) {
}
