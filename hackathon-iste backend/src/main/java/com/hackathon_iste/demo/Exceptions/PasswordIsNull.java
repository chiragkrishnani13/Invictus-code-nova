package com.hackathon_iste.demo.Exceptions;

public class PasswordIsNull extends RuntimeException {
    public PasswordIsNull() {
        super("Pasword is null!");
    }
}
