package com.swastha.swastha_track.service;

import org.springframework.stereotype.Service;

import static com.swastha.swastha_track.utility.Constants.NORMAL_WEIGHT;
import static com.swastha.swastha_track.utility.Constants.OVERWEIGHT;
import static com.swastha.swastha_track.utility.Constants.UNDERWEIGHT;

/*
    This class contains methods to calculate the Body Mass Index (BMI) and determine the BMI category based on the calculated BMI value. The calculateBMI method takes height and weight as input and returns the BMI, while the getBMICategory method takes the BMI value and returns a string indicating whether the person is underweight, normal, or overweight.
 */
@Service
public class HealthService {

    public double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }

    public String getBMICategory(double bmi) {
        if (bmi < 18.5) return UNDERWEIGHT;
        else if (bmi < 25) return NORMAL_WEIGHT;
        else return OVERWEIGHT;
    }
}
