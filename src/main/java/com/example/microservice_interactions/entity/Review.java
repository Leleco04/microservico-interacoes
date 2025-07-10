package com.example.microservice_interactions.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long reviewId;

    private Long userId;

    private Long bookId;

    private int rating;

    private String title;

    private String comment;

    private LocalDateTime createdAt;

    public Review() {}

    public Review(Long userId, Long bookId, int rating, String title, String comment, LocalDateTime createdAt) {
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.title = title;
        this.comment = comment;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return reviewId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public int getRating() {
        return rating;
    }

    public String getTitle() {
        return null;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setRating(int newRating) {
    }

    public void setTitle(String newTitle) {
    }

    public void setComment(String newComment) {
    }
}