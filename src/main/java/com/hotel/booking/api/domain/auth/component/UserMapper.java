package com.hotel.booking.api.domain.auth.component;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.web.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getCode(),
                user.getUsername(),
                user.getRole(),
                user.getPerson().getFullName(),
                user.getPerson().getPhone(),
                user.isActive()
        );
    }

}
