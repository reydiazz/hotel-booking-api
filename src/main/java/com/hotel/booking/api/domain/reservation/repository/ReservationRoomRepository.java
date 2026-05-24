package com.hotel.booking.api.domain.reservation.repository;

import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.model.entity.ReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, String> {

    Optional<ReservationRoom> findByReservation(Reservation reservation);

}
