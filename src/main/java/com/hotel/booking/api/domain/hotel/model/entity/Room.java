package com.hotel.booking.api.domain.hotel.model.entity;

import com.hotel.booking.api.domain.hotel.exception.room.*;
import com.hotel.booking.api.domain.hotel.model.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_code", nullable = false)
    private RoomType type;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status = RoomStatus.AVAILABLE;

    @Column(name = "last_cleaned", nullable = false)
    private LocalDateTime lastCleaned;

    public Room(String code, RoomType type, Integer number, Integer floor) {
        this.code = code;
        this.type = type;
        this.number = number;
        this.floor = floor;
        this.lastCleaned = LocalDateTime.now();
    }

    public void update(RoomType roomType, Integer number, Integer floor) {
        this.type = roomType;
        this.number = number;
        this.floor = floor;
    }

    public void clean() {
        if (this.status != RoomStatus.DIRTY) throw new RoomNotDirtyException();
        this.lastCleaned = LocalDateTime.now();
        markAvailable();
    }

    public void markAvailable() {
        this.status = RoomStatus.AVAILABLE;
    }

    public void sendOutOfService() {
        if (this.status != RoomStatus.AVAILABLE) throw new RoomNotAvailableException();
        this.status = RoomStatus.OUT_OF_SERVICE;
    }

    public void release() {
        if (this.status != RoomStatus.OUT_OF_SERVICE) throw new RoomNotOutOfServiceException();
        markAvailable();
    }

    public void reserve() {
        if (this.status != RoomStatus.AVAILABLE) throw new RoomNotAvailableException();
        this.status = RoomStatus.RESERVED;
    }

    public void markDirty() {
        this.status = RoomStatus.DIRTY;
    }

    public void occupy() {
        if (this.status != RoomStatus.RESERVED) throw new RoomNotReservedException();
        this.status = RoomStatus.OCCUPIED;
    }

}
