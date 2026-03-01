package com.app.fitness.Repository;

import com.app.fitness.Model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, String> {

    List<Recommendation> findByUserId(String userId);

    List<Recommendation> findByActivityId(String activityId);
}
