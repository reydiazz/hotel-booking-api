package com.hotel.booking.api.domain.reservation.repository;

import com.hotel.booking.api.domain.reservation.model.entity.Payment;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query("""
        SELECT COALESCE(SUM(p.amount), 0)
        FROM Payment p
        WHERE p.reservation = :reservation
        AND p.status = 'PAID'
       """)
    BigDecimal sumPaidPayments(Reservation reservation);

}
