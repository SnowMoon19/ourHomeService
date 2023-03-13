package com.fxj.recordmanager.common.exceptions;

public class SQLException extends Exception {
    private String message;

    public SQLException(String message) {
        this.message = message;
    }
}
