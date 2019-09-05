package com.example.todolist.exceptions;

public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException() {
        super("Description is either empty or bigger than 100 characters");
    }
}