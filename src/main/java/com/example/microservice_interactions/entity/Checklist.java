package com.example.microservice_interactions.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "checklist")
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long bookId;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Checklist() {}

    public Checklist(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
