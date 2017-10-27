package com.medicalsystem.exception;

public class ExcelImportException extends Exception {

    public ExcelImportException() {
    }

    public ExcelImportException(String message) {
        super(message);
    }

    public ExcelImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelImportException(Throwable cause) {
        super(cause);
    }

    public ExcelImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
