package com.tkouleris.contacts.exception.contact;

public class ContactNotFoundException extends Exception {
    public String message;

    public ContactNotFoundException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
