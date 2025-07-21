package com.example.microservice_interactions.dto;

public record BookDTO(
        Long id,
        String title,
        String author,
        String imagePath,
        int publishedYear
) {
}
