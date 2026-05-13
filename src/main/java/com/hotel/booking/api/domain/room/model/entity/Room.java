package com.hotel.booking.api.domain.room.model.entity;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.room.model.enums.RoomStatus;
import com.hotel.booking.api.domain.roomtype.entity.RoomType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "rooms")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_code", nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_code", nullable = false)
    private RoomType roomType;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status = RoomStatus.AVAILABLE;

    @Column(name = "last_cleaned", insertable = false, nullable = false)
    private LocalDateTime lastCleaned;

    public Room(String code, Hotel hotel, RoomType roomType, Integer number, Integer floor) {
        this.code = code;
        this.hotel = hotel;
        this.roomType = roomType;
        this.number = number;
        this.floor = floor;
    }

    public void update(RoomType roomType, Integer number, Integer floor) {
        this.roomType = roomType;
        this.number = number;
        this.floor = floor;
    }

    public void updateStatus(RoomStatus status) {
        this.status = status;
    }

    public void updateLastCleaned(LocalDateTime lastCleaned) {
        this.lastCleaned = lastCleaned;
    }

}
