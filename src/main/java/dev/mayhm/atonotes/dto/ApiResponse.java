package dev.mayhm.atonotes.dto;

import dev.mayhm.atonotes.error.ErrorDetails;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


public class ApiResponse<T> implements Serializable {

    private HttpStatus status;

    private T data;

    private ErrorDetails errorDetails;

    public ApiResponse(HttpStatus status, T data, ErrorDetails errorDetails) {
        this.status = status;
        this.data = data;
        this.errorDetails = errorDetails;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
}
