package com.hotel.booking.api.domain.reservation.component;

import com.hotel.booking.api.domain.reservation.model.entity.Payment;
import com.hotel.booking.api.domain.reservation.web.response.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getCode(),
                payment.getUser().getCode(),
                payment.getUser().getPerson().getFullName(),
                payment.getMethod(),
                payment.getStatus(),
                payment.getAmount(),
                payment.getPaymentDate()
        );
    }

}
