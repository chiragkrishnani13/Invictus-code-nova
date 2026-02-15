package com.hackathon_iste.demo.Exceptions;

public class InvalidEmail extends RuntimeException {
    public InvalidEmail() {
        super("Email should be unique");
    }
}
