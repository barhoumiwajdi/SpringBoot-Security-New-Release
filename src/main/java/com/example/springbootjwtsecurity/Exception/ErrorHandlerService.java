package com.example.springbootjwtsecurity.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandlerService extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
      public ResponseEntity<ApplicationError> handleCustomerNotFoundException(CustomerNotFoundException exception , WebRequest webrequest){
        ApplicationError error = new ApplicationError();
        error.setStatus(404);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }
}
