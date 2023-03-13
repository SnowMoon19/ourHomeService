package com.fxj.usermanager.common.exceptions;

public class SQLException extends Exception {
    private String message;

    public SQLException(String message) {
        this.message = message;
    }
}
