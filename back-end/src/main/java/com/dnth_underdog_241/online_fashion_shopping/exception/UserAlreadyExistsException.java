package com.dnth_underdog_241.online_fashion_shopping.exception;


public class UserAlreadyExistsException extends RuntimeException
{
    public UserAlreadyExistsException(String message)
    {
        super(message);
    }
}
