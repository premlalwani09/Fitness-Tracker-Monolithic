package com.app.fitness.Service.Impl;

import com.app.fitness.DTO.ActivityRequest;
import com.app.fitness.DTO.ActivityResponse;
import com.app.fitness.Model.Activity;
import com.app.fitness.Model.User;
import com.app.fitness.Repository.ActivityRepository;
import com.app.fitness.Repository.UserRepository;
import com.app.fitness.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        User userId = userRepository.findById(activityRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user: "+ activityRequest.getUserId()));

        Activity activity = Activity.builder()
                .user(userId)
                .activityType(activityRequest.getActivityType())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .build();

        Activity savedActivity = activityRepository.save(activity);

        return mapToActivityResponse(savedActivity);
    }

    @Override
    public List<ActivityResponse> getUserActivities(String userId) {

        List<Activity> activityList = activityRepository.findByUserId(userId);

        return activityList.stream()
                .map(this::mapToActivityResponse)
                .collect(Collectors.toList());
    }

    private ActivityResponse mapToActivityResponse(Activity activity) {

        return ActivityResponse.builder()
                .id(activity.getId())
                .userId(activity.getUser().getId())
                .activityType(activity.getActivityType())
                .additionalMetrics(activity.getAdditionalMetrics())
                .duration(activity.getDuration())
                .caloriesBurned(activity.getCaloriesBurned())
                .startTime(activity.getStartTime())
                .build();
    }
}
