package com.swastha.swastha_track.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastha.swastha_track.model.ProfileResponse;
import com.swastha.swastha_track.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController //controller + ResponseBody(json/xml format http response body)
@RequestMapping("/user") //base url for all endpoints in this controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/profile")
    public ProfileResponse getProfile(Authentication authentication) {
        String email = authentication.getName();
        logger.info("Received request to fetch profile for email: {}", email);
        return userService.getProfile(email);
    }
}
