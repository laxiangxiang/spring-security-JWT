package com.example.demo.exception;

/**
 * Created by LXX on 2018/12/1.
 */
public class WrongUsernameException extends RuntimeException{

    public WrongUsernameException(String message) {
        super(message);
    }

    public WrongUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
