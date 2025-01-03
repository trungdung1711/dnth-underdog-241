package com.dnth_underdog_241.online_fashion_shopping.exception;


public class UserNotFoundException
        extends RuntimeException
{
    public UserNotFoundException(Long id)
    {
        super("User not found with id " + id);
    }
}
