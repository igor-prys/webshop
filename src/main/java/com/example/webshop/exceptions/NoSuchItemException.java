package com.example.webshop.exceptions;

public class NoSuchItemException extends RuntimeException{
    public NoSuchItemException(String message){
        super(message);
    }
}
