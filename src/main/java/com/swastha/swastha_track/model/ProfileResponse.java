package com.swastha.swastha_track.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse{
    String firstName;
    String lastName;
    String gender;
    int age;
    double weight;
    double height;
    double bmi;
    String category;
}
