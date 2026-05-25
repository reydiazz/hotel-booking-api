package com.hotel.booking.api.domain.hotel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "room_types")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomType {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    public RoomType(String code, String name, String description, Integer capacity, BigDecimal basePrice) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.basePrice = basePrice;
    }

    public void update(String name, String description, Integer capacity, BigDecimal basePrice) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.basePrice = basePrice;
    }

}
