package com.app.fitness.Repository;

import com.app.fitness.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, String> {

    List<Activity> findByUserId(String userId);
}
