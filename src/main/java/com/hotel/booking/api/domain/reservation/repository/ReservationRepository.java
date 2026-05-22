package com.hotel.booking.api.domain.reservation.repository;

import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,String> {
}
