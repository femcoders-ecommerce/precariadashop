package com.precariada.precariadashop.exceptions;

public class ErrorResponse {
    private String message;
    private int status;
    private String errorCode;
    private String timestamp;
    private String path;

    public ErrorResponse(String message, int status, String errorCode, String timestamp, String path) {
        this.message = message;
        this.status = status;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
