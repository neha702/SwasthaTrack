package com.swastha.swastha_track.service;

import org.springframework.stereotype.Service;

/*
    This class contains methods to calculate the Body Mass Index (BMI) and determine the BMI category based on the calculated BMI value. The calculateBMI method takes height and weight as input and returns the BMI, while the getBMICategory method takes the BMI value and returns a string indicating whether the person is underweight, normal, or overweight.
 */
@Service
public class HealthService {

    public double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }

    public String getBMICategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else return "Overweight";
    }
}
