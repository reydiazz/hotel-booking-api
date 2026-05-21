package com.hotel.booking.api.domain.auth.component;

import com.hotel.booking.api.domain.auth.web.response.AuthResponse;
import com.hotel.booking.api.domain.auth.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthResponse toResponse(User user){
        return new AuthResponse(
                user.getCode(),
                user.getUsername(),
                user.getEmployee().getPerson().getFirstName(),
                user.getEmployee().getPerson().getLastName(),
                user.getRole()
        );
    }
}
