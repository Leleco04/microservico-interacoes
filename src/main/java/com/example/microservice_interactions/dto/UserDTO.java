package com.example.microservice_interactions.dto;

public record UserDTO(
        Long id,
        String username,
        String role
) {}