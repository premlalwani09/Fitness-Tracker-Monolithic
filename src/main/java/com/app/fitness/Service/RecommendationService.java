package com.app.fitness.Service;

import com.app.fitness.DTO.RecommendationRequest;
import com.app.fitness.DTO.RecommendationResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface RecommendationService {

    RecommendationResponse generateRecommendation(RecommendationRequest request);

    List<RecommendationResponse> getUserRecommendations(String userId);

    List<RecommendationResponse> getActivityRecommendations(String activityId);
}
