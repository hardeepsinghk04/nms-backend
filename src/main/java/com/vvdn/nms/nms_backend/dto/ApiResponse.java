package com.vvdn.nms.nms_backend.dto;

/**
 * A generic wrapper for all API responses.
 */
public class ApiResponse<T> {
    private boolean status;
    private String responseCode;
    private String message;
    private T data;

    public ApiResponse() {}

    public ApiResponse(boolean status, String responseCode, String message, T data) {
        this.status = status;
        this.responseCode = responseCode;
        this.message = message;
        this.data = data;
    }

    // Explicit getters and setters

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
