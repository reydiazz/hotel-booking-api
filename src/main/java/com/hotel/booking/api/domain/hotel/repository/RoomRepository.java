package com.hotel.booking.api.domain.hotel.repository;

import com.hotel.booking.api.domain.hotel.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {

    boolean existsByNumber(Integer number);

    boolean existsByNumberAndCodeNot(Integer number, String code);

}
