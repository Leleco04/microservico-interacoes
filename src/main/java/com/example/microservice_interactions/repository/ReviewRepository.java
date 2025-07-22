package com.example.microservice_interactions.repository;

import com.example.microservice_interactions.entity.Like;
import com.example.microservice_interactions.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUserId(Long userId);

    Page<Review> findByBookId(Long bookId, Pageable pageable);

    long countByBookId(Long bookId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.bookId = :bookId")
    Double findAverageRatingByBookId(@Param("bookId") Long bookId);
}
