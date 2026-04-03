package com.swastha.swastha_track.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swastha.swastha_track.entity.User;
import com.swastha.swastha_track.model.LoginRequest;
import com.swastha.swastha_track.model.RegisterRequest;
import com.swastha.swastha_track.repository.UserRepository;
import com.swastha.swastha_track.security.JwtUtil;
import static com.swastha.swastha_track.utility.Constants.EMAIL_ALREADY_EXISTS;
import static com.swastha.swastha_track.utility.Constants.INVALID_CREDENTIALS;
import static com.swastha.swastha_track.utility.Constants.SUCCESS_USER_REGISTERED;
import static com.swastha.swastha_track.utility.Constants.USER_NOT_FOUND;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    // Register user
    public String register(RegisterRequest request) {
        logger.debug("Validating register request");

        //Validation of request => user existence based on email id. If email id is already registered then we will throw an exception.
        if (userRepository.findByEmailId(request.getEmailId()).isPresent()) {
            throw new IllegalArgumentException(EMAIL_ALREADY_EXISTS);
        }

        //Build user entity
        logger.debug("Building user entity...");
        User user = User.builder()
                .emailId(request.getEmailId())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .age(request.getAge())
                .weight(request.getWeight())
                .height(request.getHeight())
                .build();

        userRepository.save(user);
        logger.info("Register request processed successfully for email: {}", request.getEmailId());

        return SUCCESS_USER_REGISTERED;
    }

    // Login user
    public String login(LoginRequest request) {
        logger.debug("Validating request for login");
        //Validation of request => User existence based on email id. If email id is not registered then we will throw an exception.
        User user = userRepository.findByEmailId(request.getEmailId())
                .orElseThrow(() -> new IllegalArgumentException(USER_NOT_FOUND));

        //Password check
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException(INVALID_CREDENTIALS);
        }

        logger.info("Building jwt token successfully for email: {}", request.getEmailId());
        //If all good then token is generated and returned to client
        return jwtUtil.generateToken(user.getEmailId());
    }
}
