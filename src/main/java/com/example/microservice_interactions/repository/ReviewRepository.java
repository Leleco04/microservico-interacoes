package com.example.microservice_interactions.repository;

import com.example.microservice_interactions.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
