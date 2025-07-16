package com.example.microservice_interactions.dto;

import com.example.microservice_interactions.enums.Status;
import java.time.LocalDate;

public record ChecklistRequestDTO (
        Long userId,
        Long bookId,
        LocalDate createdAt,
        String status
) {
}
