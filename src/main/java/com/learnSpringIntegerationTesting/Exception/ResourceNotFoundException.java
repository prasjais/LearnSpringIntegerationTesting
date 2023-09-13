package com.learnSpringIntegerationTesting.Exception;

import static java.lang.String.format;

public class ResourceNotFoundException extends RuntimeException{

    private int id;

    public ResourceNotFoundException(int id)
    {
        super(String.format("resource with this id %i not found", id));
        this.id = id;
    }
}
