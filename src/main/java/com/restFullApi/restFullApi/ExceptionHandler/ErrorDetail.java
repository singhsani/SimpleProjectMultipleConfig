package com.restFullApi.restFullApi.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetail {
    private LocalDateTime timestamp;
    private String message;
    private String detail;

    public ErrorDetail(LocalDateTime timestamp, String message, String detail) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ErrorDetail{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
