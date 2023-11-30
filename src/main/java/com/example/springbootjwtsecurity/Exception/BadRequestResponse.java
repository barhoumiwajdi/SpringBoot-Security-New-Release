package com.example.springbootjwtsecurity.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestResponse  extends RuntimeException{
    public BadRequestResponse(String message){
        super(message );

    }

}
