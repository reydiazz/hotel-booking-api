package com.hotel.booking.api.domain.auth.component;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.web.response.UserResponse;
import com.hotel.booking.api.domain.person.component.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PersonMapper person;

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getCode(),
                user.getUsername(),
                user.getRole(),
                user.isActive(),
                person.toResponse(user.getPerson())
        );
    }

}
