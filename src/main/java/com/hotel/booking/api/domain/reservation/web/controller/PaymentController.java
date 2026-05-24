package com.hotel.booking.api.domain.reservation.web.controller;

import com.hotel.booking.api.domain.reservation.service.PaymentService;
import com.hotel.booking.api.domain.reservation.web.request.CreatePaymentRequest;
import com.hotel.booking.api.domain.reservation.web.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','RECEPTION')")
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<PaymentResponse> create(CreatePaymentRequest request){
        PaymentResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
