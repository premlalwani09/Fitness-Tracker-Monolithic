package com.app.fitness.Controller;

import com.app.fitness.DTO.RecommendationRequest;
import com.app.fitness.DTO.RecommendationResponse;
import com.app.fitness.Service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    @Autowired
    public RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendation(@Valid @RequestBody RecommendationRequest request){
        return new ResponseEntity<>(recommendationService.generateRecommendation(request), HttpStatus.CREATED);
    }

    @GetMapping("/userRecommendations")
    public ResponseEntity<List<RecommendationResponse>> getUserRecommendations(@RequestHeader String userId){
        return ResponseEntity.ok(recommendationService.getUserRecommendations(userId));
    }

    @GetMapping("/activityRecommendations")
    public ResponseEntity<List<RecommendationResponse>> getActivityRecommendations(@RequestHeader String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecommendations(activityId));
    }
}
