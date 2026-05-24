package com.hotel.booking.api.domain.reservation.service;

import com.hotel.booking.api.domain.hotel.model.entity.Room;
import com.hotel.booking.api.domain.hotel.model.enums.RoomStatus;
import com.hotel.booking.api.domain.hotel.service.RoomService;
import com.hotel.booking.api.domain.reservation.exception.ReservationNotFoundException;
import com.hotel.booking.api.domain.reservation.exception.ReservationRoomDirtyException;
import com.hotel.booking.api.domain.reservation.exception.ReservationRoomOccupiedException;
import com.hotel.booking.api.domain.reservation.exception.ReservationRoomOutOfServiceException;
import com.hotel.booking.api.domain.reservation.model.entity.Reservation;
import com.hotel.booking.api.domain.reservation.model.entity.ReservationRoom;
import com.hotel.booking.api.domain.reservation.repository.ReservationRoomRepository;
import com.hotel.booking.api.domain.reservation.web.request.CreateReservationRoomRequest;
import com.hotel.booking.api.shared.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationRoomService {

    public static final String PREFIX = "RVR";

    private final ReservationRoomRepository repository;
    private final RoomService roomService;

    @Transactional
    public ReservationRoom create(Reservation reservation, CreateReservationRoomRequest request){
        Room room = roomService.findByCodeOrThrow(request.roomCode());
        validateStatusRoom(room);
        BigDecimal subtotal = request.pricePerNight().multiply(BigDecimal.valueOf(request.nights()));
        reservation.addToTotalAmount(subtotal);
        room.updateStatus(RoomStatus.OCCUPIED);
        ReservationRoom reservationRoom = new ReservationRoom(
                CodeGenerator.next(PREFIX),
                reservation,
                room,
                request.pricePerNight(),
                request.nights()
        );
        return repository.save(reservationRoom);
    }

    @Transactional
    public void cancel(Reservation reservation){
        Optional<ReservationRoom> reservationRoom = repository.findByReservation(reservation);
        reservationRoom.ifPresent(room -> room.getRoom().updateStatus(RoomStatus.AVAILABLE));
    }

    @Transactional
    public void done(Reservation reservation){
        Optional<ReservationRoom> reservationRoom = repository.findByReservation(reservation);
        reservationRoom.ifPresent(room -> room.getRoom().updateStatus(RoomStatus.DIRTY));
    }

    private void validateStatusRoom(Room room) {
        if (room.getStatus() == RoomStatus.OCCUPIED) {
            throw new ReservationRoomOccupiedException(room.getCode());
        }
        if (room.getStatus() == RoomStatus.DIRTY) {
            throw new ReservationRoomDirtyException(room.getCode());
        }
        if (room.getStatus() == RoomStatus.OUT_OF_SERVICE) {
            throw new ReservationRoomOutOfServiceException(room.getCode());
        }
    }


}
