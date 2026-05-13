package com.hotel.booking.api.domain.room.repository;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.room.model.entity.Room;
import com.hotel.booking.api.domain.roomtype.entity.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {

    Page<Room> findByHotel(Hotel hotel, Pageable pageable);

}
