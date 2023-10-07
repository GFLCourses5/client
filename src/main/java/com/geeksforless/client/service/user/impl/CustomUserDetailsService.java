package com.geeksforless.client.service.user.impl;

import com.geeksforless.client.exception.UserNotFoundException;
import com.geeksforless.client.model.entity.CustomUserDetails;
import com.geeksforless.client.model.entity.User;
import com.geeksforless.client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new UserNotFoundException(
                        String.format("User with email %s not found", email)
                ));
        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }
}
