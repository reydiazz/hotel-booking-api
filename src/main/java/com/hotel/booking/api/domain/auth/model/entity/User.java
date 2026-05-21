package com.hotel.booking.api.domain.auth.model.entity;

import com.hotel.booking.api.domain.person.model.entity.Employee;
import com.hotel.booking.api.domain.auth.model.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "app_users")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @OneToOne
    @JoinColumn(name = "employee_code", unique = true)
    private Employee employee;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public User(String code, String username, String password, Role role, Employee employee) {
        this.code = code;
        this.username = username;
        this.password = password;
        this.role = role;
        this.employee = employee;
    }

    public void update(Boolean active, Employee employee) {
        this.active = active;
        this.employee = employee;
    }

}
