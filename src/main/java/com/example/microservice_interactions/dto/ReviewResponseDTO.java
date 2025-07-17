package com.example.microservice_interactions.dto;

import com.example.microservice_interactions.entity.Review;

import java.util.List;

public record ReviewResponseDTO(
        List<Review> reviews,
        long total,
        double avg
) {
}
