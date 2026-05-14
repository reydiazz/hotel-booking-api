package com.hotel.booking.api.domain.hotel.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "hotels")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Hotel {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "phone")
    private String phone;

    public Hotel(String code, String name, String address, String city, String country, String phone) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    public void update(String name, String address, String city, String country, String phone) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

}
