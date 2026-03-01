package com.app.fitness.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationRequest {

    @NotBlank(message = "User Id is required")
    private String userId;

    @NotBlank(message = "Activity Id is required")
    private String activityId;

    @NotBlank(message = "Type is required")
    private String type;
    private String recommendation;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;

}
