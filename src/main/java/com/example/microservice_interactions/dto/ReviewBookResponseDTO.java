package com.example.microservice_interactions.dto;

import java.time.LocalDateTime;

public record ReviewBookResponseDTO(
        Long userId,
        Long bookId,
        int rating,
        String title,
        String comment,
        String username,
        BookDTO book,
        LocalDateTime createdAt
) {
}
