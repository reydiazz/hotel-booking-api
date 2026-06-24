package com.hotel.booking.api.domain.auth.model.entity;

import com.hotel.booking.api.domain.auth.model.enums.Role;
import com.hotel.booking.api.domain.person.model.entity.Person;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @OneToOne
    @JoinColumn(name = "person_code", unique = true)
    private Person person;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public User(String code, String username, String password, Role role, Person person) {
        this.code = code;
        this.username = username;
        this.password = password;
        this.role = role;
        this.person = person;
    }

    public void update(Person person, Role role) {
        this.role = role;
        this.person = person;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

}
