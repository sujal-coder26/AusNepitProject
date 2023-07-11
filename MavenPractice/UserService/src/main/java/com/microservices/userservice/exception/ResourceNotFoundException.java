package com.microservices.userservice.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mesasge){
        super(mesasge);
    }
}
