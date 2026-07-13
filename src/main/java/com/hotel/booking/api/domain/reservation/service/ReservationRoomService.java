package com.hotel.booking.api.domain.reservation.service;

import com.hotel.booking.api.domain.hotel.model.entity.Room;
import com.hotel.booking.api.domain.hotel.service.RoomService;
import com.hotel.booking.api.domain.reservation.exception.reservation.room.ReservationRoomNotFoundException;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.model.entity.ReservationRoom;
import com.hotel.booking.api.domain.reservation.repository.ReservationRoomRepository;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRoomRequest;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationRoomService {

    public static final String PREFIX = "RVR";
    private final ReservationRoomRepository repository;

    private final RoomService roomService;

    @Transactional
    public ReservationRoom create(Reservation reservation, CreateReservationRoomRequest request) {
        Room room = roomService.findByCodeOrThrow(request.roomCode());
        room.reserve();
        reservation.defineTotalAmount(room.getType().getBasePrice(), request.nights());
        String code = CodeGenerator.next(PREFIX);
        ReservationRoom reservationRoom = new ReservationRoom(code, reservation, room, room.getType().getBasePrice(), request.nights());
        return repository.save(reservationRoom);
    }

    @Transactional
    public void occupy(Reservation reservation) {
        ReservationRoom reservationRoom = findByReservationOrThrow(reservation);
        reservationRoom.getRoom().occupy();
    }

    @Transactional
    public void cancel(Reservation reservation) {
        ReservationRoom reservationRoom = findByReservationOrThrow(reservation);
        reservationRoom.getRoom().release();
    }

    @Transactional
    public void done(Reservation reservation) {
        ReservationRoom reservationRoom = findByReservationOrThrow(reservation);
        reservationRoom.getRoom().markDirty();
    }

    public ReservationRoom findByReservationOrThrow(Reservation reservation) {
        return repository.findByReservation(reservation).orElseThrow(
                ReservationRoomNotFoundException::new
        );
    }

}
