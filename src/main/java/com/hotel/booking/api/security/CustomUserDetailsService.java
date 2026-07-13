package com.hotel.booking.api.security;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @__({@Lazy}))
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = service.findByUsername(username);
        return new UserPrincipal(user);
    }

}
