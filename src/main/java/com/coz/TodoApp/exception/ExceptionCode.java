package com.coz.TodoApp.exception;

import lombok.Getter;

public enum ExceptionCode {
    TODO_NOT_FOUND(404, "TO-DO Not Found"),
    TODO_EXISTS(409, "TO-DO Exists"),
    NOT_IMPLEMENTATION(501, "Not Implementation");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
