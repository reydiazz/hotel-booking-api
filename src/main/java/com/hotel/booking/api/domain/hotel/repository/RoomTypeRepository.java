package com.hotel.booking.api.domain.hotel.repository;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.hotel.model.entity.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, String> {

    Page<RoomType> findByHotel(Hotel hotel, Pageable pageable);

}
