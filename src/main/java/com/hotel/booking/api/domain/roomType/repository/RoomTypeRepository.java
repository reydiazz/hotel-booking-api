package com.hotel.booking.api.domain.roomType.repository;

import com.hotel.booking.api.domain.roomType.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, String> {}
