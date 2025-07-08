package com.example.microservice_interactions.dto;

import java.time.LocalDate;

public record ChecklistRequestDTO (
        Long userId,
        Long bookId,
        LocalDate createdAt
) {
}
