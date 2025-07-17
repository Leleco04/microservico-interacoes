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

    private String username;

    private LocalDateTime createdAt;

    public Review() {}

    public Review(Long userId, Long bookId, int rating, String title, String comment, String username, LocalDateTime createdAt) {
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.title = title;
        this.comment = comment;
        this.username = username;
        this.createdAt = createdAt;
    }


    public Long getReviewId() {
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
        return title;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() { return username;}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setRating(int newRating) {
        this.rating = newRating;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setComment(String newComment) {
        this.comment = newComment;
    }
}