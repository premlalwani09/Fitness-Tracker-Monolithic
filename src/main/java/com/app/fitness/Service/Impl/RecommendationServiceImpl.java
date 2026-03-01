package com.app.fitness.Service.Impl;

import com.app.fitness.DTO.RecommendationRequest;
import com.app.fitness.DTO.RecommendationResponse;
import com.app.fitness.Model.Activity;
import com.app.fitness.Model.Recommendation;
import com.app.fitness.Model.User;
import com.app.fitness.Repository.ActivityRepository;
import com.app.fitness.Repository.RecommendationRepository;
import com.app.fitness.Repository.UserRepository;
import com.app.fitness.Service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    public RecommendationRepository recommendationRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ActivityRepository activityRepository;


    @Override
    public RecommendationResponse generateRecommendation(RecommendationRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not Found: " + request.getUserId()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not Found: " + request.getActivityId()));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .type(request.getType())
                .recommendation(request.getRecommendation())
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        Recommendation savedRecommendation = recommendationRepository.save(recommendation);

        return mapToRecommendationResponse(savedRecommendation);
    }

    @Override
    public List<RecommendationResponse> getUserRecommendations(String userId) {

        List<Recommendation> recommendationList = recommendationRepository.findByUserId(userId);

        return recommendationList.stream()
                .map(this::mapToRecommendationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecommendationResponse> getActivityRecommendations(String activityId) {

        List<Recommendation> recommendationList = recommendationRepository.findByActivityId(activityId);

        return recommendationList.stream()
                .map(this::mapToRecommendationResponse)
                .collect(Collectors.toList());
    }

    private RecommendationResponse mapToRecommendationResponse(Recommendation savedRecommendation) {

        return RecommendationResponse.builder()
                .id(savedRecommendation.getId())
                .userId(savedRecommendation.getUser().getId())
                .activityId(savedRecommendation.getActivity().getId())
                .type(savedRecommendation.getType())
                .recommendation(savedRecommendation.getRecommendation())
                .improvements(savedRecommendation.getImprovements())
                .suggestions(savedRecommendation.getSuggestions())
                .safety(savedRecommendation.getSafety())
                .build();
    }
}
