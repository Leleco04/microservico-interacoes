package com.example.microservice_interactions.repository;

import com.example.microservice_interactions.entity.Checklist;
import com.example.microservice_interactions.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {


    boolean existsByUserIdAndBookId(Long userId, Long bookId);

    Optional<Like> findByUserIdAndBookId(Long userId, Long bookId);

    List<Like> findByUserId(Long userId);

}
