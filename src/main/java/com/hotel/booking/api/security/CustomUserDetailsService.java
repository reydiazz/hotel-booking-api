package com.hotel.booking.api.security;

import com.hotel.booking.api.domain.auth.model.entity.User;
import com.hotel.booking.api.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;
    private final String message = "USER_NOT_FOUND";

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(message)
        );
        return new UserPrincipal(user);
    }
}
