package com.app.fitness.Service;

import com.app.fitness.DTO.ActivityRequest;
import com.app.fitness.DTO.ActivityResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ActivityService {


    ActivityResponse trackActivity(ActivityRequest activityRequest);

    List<ActivityResponse> getUserActivities(String userId);
}
