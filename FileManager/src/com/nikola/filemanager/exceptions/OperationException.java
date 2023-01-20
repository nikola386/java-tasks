package com.nikola.filemanager.exceptions;

public class OperationException extends Exception {
    public OperationException(String errMessage) {
        super(errMessage);
    }
    public OperationException(String errMessage, Throwable err) {
        super(errMessage, err);
    }
}
