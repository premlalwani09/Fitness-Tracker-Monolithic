package com.app.fitness.DTO;

import com.app.fitness.Enum.ActivityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    @NotBlank(message = "User Id is required")
    private String userId;

    @NotNull(message = "Activity Type is required")
    private ActivityType activityType;
    private Map<String, Object> additionalMetrics;

    @NotNull(message = "Duration is required")
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
}
