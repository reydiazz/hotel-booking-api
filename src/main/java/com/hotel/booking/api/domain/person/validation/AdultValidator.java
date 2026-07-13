package com.hotel.booking.api.domain.person.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

    private int minimumAge;

    @Override
    public void initialize(Adult annotation) {
        this.minimumAge = annotation.age();
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return true;
        }
        return !birthDate.plusYears(minimumAge).isAfter(LocalDate.now());
    }


}
