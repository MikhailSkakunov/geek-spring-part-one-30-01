package ru.geekbrains.rest;

import java.time.LocalDateTime;

public class ErrorDto {

    private String message;

    private final LocalDateTime localDateTime;

    public ErrorDto() {
        this.localDateTime = LocalDateTime.now();
    }

    public ErrorDto(String message) {
        this();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
