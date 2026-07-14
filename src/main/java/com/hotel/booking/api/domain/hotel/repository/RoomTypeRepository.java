package com.hotel.booking.api.domain.hotel.repository;

import com.hotel.booking.api.domain.hotel.model.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, String> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndCodeNot(String name, String code);

}
