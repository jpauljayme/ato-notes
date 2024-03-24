package dev.mayhm.atonotes.dto;

import org.springframework.http.HttpStatus;

import java.io.Serializable;


public class ApiResponse<T> implements Serializable {

    private HttpStatus status;

    private T data;

    public ApiResponse(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
    }

}
