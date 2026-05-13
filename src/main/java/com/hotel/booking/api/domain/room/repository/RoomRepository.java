package com.hotel.booking.api.domain.room.repository;

import com.hotel.booking.api.domain.room.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {}
