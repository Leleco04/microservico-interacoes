package com.example.microservice_interactions.repository;

import com.example.microservice_interactions.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserIdAndBookId(Long userId, Long bookId);
}
