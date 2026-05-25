package com.hotel.booking.api.domain.hotel.model.entity;

import com.hotel.booking.api.domain.hotel.exception.room.RoomReservedException;
import com.hotel.booking.api.domain.hotel.model.enums.RoomStatus;
import com.hotel.booking.api.domain.hotel.exception.room.RoomDirtyException;
import com.hotel.booking.api.domain.hotel.exception.room.RoomOccupiedException;
import com.hotel.booking.api.domain.hotel.exception.room.RoomOutOfServiceException;
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
        this.lastCleaned = LocalDateTime.now();
        release();
    }

    public void release() {
        this.status = RoomStatus.AVAILABLE;
    }

    public void reserve() {
        ensureAvailableForReservation();
        this.status = RoomStatus.RESERVED;
    }

    public void markDirty() {
        this.status = RoomStatus.DIRTY;
    }

    public void occupy() {
        this.status = RoomStatus.OCCUPIED;
    }

    public void sendOutOfService() {
        this.status = RoomStatus.OUT_OF_SERVICE;
    }

    private void ensureAvailableForReservation() {
        switch (this.status) {
            case DIRTY -> throw new RoomDirtyException(this.code);
            case RESERVED -> throw new RoomReservedException(this.code);
            case OCCUPIED -> throw new RoomOccupiedException(this.code);
            case OUT_OF_SERVICE -> throw new RoomOutOfServiceException(this.code);
        }
    }

}
