package com.example.microservice_interactions.dto;

public record EditReviewDTO(
        Long reviewId,
        int newRating,
        String newTitle,
        String newComment
) {}
