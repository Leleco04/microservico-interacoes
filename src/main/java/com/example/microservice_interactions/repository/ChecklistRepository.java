package com.example.microservice_interactions.repository;

import com.example.microservice_interactions.entity.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {

    boolean existsByUserIdAndBookId(Long userId, Long bookId);

    Optional<Checklist> findByUserIdAndBookId(Long userId, Long bookId);
}
