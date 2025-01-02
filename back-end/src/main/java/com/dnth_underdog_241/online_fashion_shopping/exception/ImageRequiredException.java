package com.dnth_underdog_241.online_fashion_shopping.exception;


public class ImageRequiredException extends RuntimeException {
    public ImageRequiredException() {
        super("Image is required");
    }
}
