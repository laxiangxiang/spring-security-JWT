package com.example.demo.exception;

/**
 * Created by LXX on 2018/12/1.
 */
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
