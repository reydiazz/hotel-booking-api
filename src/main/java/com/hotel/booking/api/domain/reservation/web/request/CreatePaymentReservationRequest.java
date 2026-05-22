package com.hotel.booking.api.domain.reservation.web.request;

import com.hotel.booking.api.domain.reservation.model.enums.PaymentMethod;
import com.hotel.booking.api.domain.reservation.model.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePaymentReservationRequest(
        @NotNull(message = "Payment method not be empty")
        PaymentMethod method,
        @NotNull(message = "Payment status not be empty")
        PaymentStatus status,
        @NotNull(message = "Payment amount not be empty")
        BigDecimal amount
) {
}
