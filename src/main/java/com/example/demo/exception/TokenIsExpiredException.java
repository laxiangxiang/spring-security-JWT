package com.example.demo.exception;

/**
 * Created by LXX on 2018/10/22.
 */
public class TokenIsExpiredException extends RuntimeException {

    public TokenIsExpiredException(String msg){
        super(msg);
    }

    public TokenIsExpiredException(String msg, Throwable t){
        super(msg,t);
    }
}
