package com.example.microservice_interactions.enums;

public enum Status {
    READ("READ"),
    LIKE("LIKE"),
    WANT_TO_READ("WANT TO READ");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
