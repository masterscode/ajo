package com.ajo.payloads.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> implements Serializable {
    private HttpStatus status;
    private String message;
    private String error;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final Timestamp timestamp;
    private String debugMessage;
    private T data;

    private ApiResponse() {
        timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public ApiResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.error = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiResponse(T data, String message, HttpStatus status) {
        this();
        this.status = status;
        this.message = message;
        this.data = data;
    }


}

