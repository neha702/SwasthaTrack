package com.swastha.swastha_track.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swastha.swastha_track.entity.User;
import com.swastha.swastha_track.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/*
    This class contains methods to load user details from the database based on the email. It implements the UserDetailsService interface, which is a core interface in Spring Security for retrieving user-related data. The loadUserByUsername method is overridden to fetch the user from the database using the UserRepository and return a UserDetails object that Spring Security can use for authentication and authorization purposes.
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmailId(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmailId(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}