package com.tokio.demo.dto.api;
import java.time.LocalDateTime;

public class ApiErrorDto {
        private int status;
        private String message;
        private LocalDateTime timestamp;

        public ApiErrorDto(int status, String message) {
            this.status = status;
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }

        // getters
        public int getStatus() { return status; }
        public String getMessage() { return message; }
        public LocalDateTime getTimestamp() { return timestamp; }
    }


