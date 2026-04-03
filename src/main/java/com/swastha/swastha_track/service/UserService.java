package com.swastha.swastha_track.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.swastha.swastha_track.entity.User;
import com.swastha.swastha_track.model.ProfileResponse;
import com.swastha.swastha_track.repository.UserRepository;
import static com.swastha.swastha_track.utility.Constants.USER_NOT_FOUND;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HealthService healthService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    //This method fetches user level information from email and also compute bmi and category to which it belongs to.
    public ProfileResponse getProfile(String email) {
        //Validation for user existence based on email id. If email id is not registered then we will throw an exception.
        logger.debug("Validation for user existence based on email id");
        User user = userRepository.findByEmailId(email)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

        //Heath service is responsible for calculating BMI and assigning BMI category accordingly.
        logger.debug("Health service is called for calculating BMI and assigning BMI category accordingly");
        double bmi = healthService.calculateBMI(user.getHeight(), user.getWeight());
        String category = healthService.getBMICategory(bmi);

        logger.info("Building profile for user: {}",user.getFirstName());
        return ProfileResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .gender(user.getGender())
                .height(user.getHeight())
                .weight(user.getWeight())
                .bmi(bmi)
                .category(category)
                .build();
    }
}
