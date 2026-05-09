package com.hotel.booking.api.domain.hotel.repository;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {}
