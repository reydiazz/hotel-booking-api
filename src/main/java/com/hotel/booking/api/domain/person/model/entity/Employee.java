package com.hotel.booking.api.domain.person.model.entity;

import com.hotel.booking.api.domain.hotel.model.entity.Hotel;
import com.hotel.booking.api.domain.person.model.enums.EmployeePosition;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "employees")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_code", nullable = false)
    private Hotel hotel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_code", nullable = false)
    private Person person;

    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private EmployeePosition position;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    public Employee(String code, Hotel hotel, Person person, EmployeePosition position, BigDecimal salary) {
        this.code = code;
        this.hotel = hotel;
        this.person = person;
        this.position = position;
        this.salary = salary;
    }

    public void update(Hotel hotel, Person person, EmployeePosition position, BigDecimal salary) {
        this.hotel = hotel;
        this.person = person;
        this.position = position;
        this.salary = salary;
    }

}
