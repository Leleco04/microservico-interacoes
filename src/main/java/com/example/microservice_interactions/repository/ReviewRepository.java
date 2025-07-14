package com.example.microservice_interactions.repository;

import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);

    List<Review> findByBookId(Long bookId);
}
