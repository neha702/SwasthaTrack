package com.swastha.swastha_track.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastha.swastha_track.model.LoginRequest;
import com.swastha.swastha_track.model.RegisterRequest;
import com.swastha.swastha_track.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController //controller + ResponseBody(json/xml format http response body)
@RequestMapping("/auth") //base url for all endpoints in this controller
@RequiredArgsConstructor
public class AuthController {
    public final AuthService  authService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    //Register endpoint => For this method its like saving data of user in database
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        logger.info("Received register request");
        String message = authService.register(request);
        return ResponseEntity.status(200).body(Map.of("message", message));
    }

    //Login endpoint => For this endpoint we will check if the user exists in database and if the password is correct then we will generate a JWT token and return it to the client. This token can be used for subsequent requests to access protected resources.
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        logger.info("Received login request");
        String token = authService.login(request);
        return ResponseEntity.status(200).body(Map.of("token", token));
    }

}
