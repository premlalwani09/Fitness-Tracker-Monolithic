package com.app.fitness.Controller;

import com.app.fitness.DTO.ActivityRequest;
import com.app.fitness.DTO.ActivityResponse;
import com.app.fitness.Service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    public ActivityService activityService;

    @PostMapping("/trackActivity")
    public ResponseEntity<ActivityResponse> trackActivity(@Valid @RequestBody ActivityRequest activityRequest){
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

    @GetMapping("/userActivities")
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader String userId){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}
