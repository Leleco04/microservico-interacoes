package com.example.microservice_interactions.dto;

import java.time.LocalDateTime;

public record ReviewRequestDTO(
        Long userId,
        Long bookId,
        int rating,
        String title,
        String comment,
        LocalDateTime createdAt)
{

}
