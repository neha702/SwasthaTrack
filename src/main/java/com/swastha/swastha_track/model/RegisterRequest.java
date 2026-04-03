package com.swastha.swastha_track.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String emailId;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 72, message = "Password must be between 8 and 72 characters")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Min(value = 1, message = "Age must be positive")
    @Max(value = 120, message = "Age must be <= 120")
    private int age;

    @Positive(message = "Weight must be positive")
    @DecimalMax(value = "500.0", message = "Weight must be <= 500kg")
    private double weight;

    @Positive(message = "Height must be positive")
    @DecimalMax(value = "300.0", message = "Height must be <= 300cm")
    private double height;
}
