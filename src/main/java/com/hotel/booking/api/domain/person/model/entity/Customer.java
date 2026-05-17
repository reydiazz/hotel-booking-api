package com.hotel.booking.api.domain.person.model.entity;

import com.hotel.booking.api.domain.person.model.enums.DocumentTypeCustomer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "customers")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_code", nullable = false)
    private Person person;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type ", nullable = false)
    private DocumentTypeCustomer documentType;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    public Customer(String code, Person person, DocumentTypeCustomer documentType, String documentNumber) {
        this.code = code;
        this.person = person;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

    public void update(Person person, DocumentTypeCustomer documentType, String documentNumber) {
        this.person = person;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
    }

}
