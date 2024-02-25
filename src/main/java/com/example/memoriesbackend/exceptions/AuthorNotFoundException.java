package com.example.memoriesbackend.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
