package com.swastha.swastha_track.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data //Generate getters and setters and other constructor
public class LoginRequest {
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String emailId;

    @NotBlank(message = "Password is required")
    private String password;
}
