package com.hotel.booking.api.domain.reservation.service;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.service.AuthService;
import com.hotel.booking.api.domain.reservation.component.PaymentMapper;
import com.hotel.booking.api.domain.reservation.model.entity.Payment;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.model.enums.PaymentStatus;
import com.hotel.booking.api.domain.reservation.repository.PaymentRepository;
import com.hotel.booking.api.domain.reservation.web.request.CreatePaymentRequest;
import com.hotel.booking.api.domain.reservation.web.response.PaymentResponse;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public static final String PREFIX = "PYM";
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    private final AuthService authService;
    private final ReservationService reservationService;

    @Transactional
    public PaymentResponse create(CreatePaymentRequest request) {
        Reservation reservation = reservationService.findByCodeOrThrow(request.reservationCode());
        String code = CodeGenerator.next(PREFIX);
        User user = authService.getAuthenticatedUser();
        BigDecimal paid = repository.sumPaidPayments(reservation);
        reservation.validatePayment(paid, request.amount());
        Payment payment = new Payment(code, user, reservation, request.method(), PaymentStatus.PAID, request.amount());
        Payment saved = repository.save(payment);
        return mapper.toResponse(saved);
    }

}
