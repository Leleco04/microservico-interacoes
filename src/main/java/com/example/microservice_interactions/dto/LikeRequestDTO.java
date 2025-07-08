package com.example.microservice_interactions.dto;

import java.time.LocalDate;

public record LikeRequestDTO (
        Long userId,
        Long bookId,
        LocalDate createdAt
) {
}
