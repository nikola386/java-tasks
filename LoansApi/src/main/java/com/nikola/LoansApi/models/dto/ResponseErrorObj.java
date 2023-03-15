package com.nikola.LoansApi.models.dto;

public class ResponseErrorObj {
    private String message;

    public ResponseErrorObj(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
