package com.example.microservice_interactions.enums;

public enum Status {
    READ("LIVRO LIDO"),
    WANT_TO_READ("PARA LER");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
