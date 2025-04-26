package com.practice.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.practice.Dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                new Date(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFound e, WebRequest request) {
        ErrorDetails error = new ErrorDetails(
                e.getMessage(),               // Corrected getmsg() to getMessage()
                new Date(),                   // Corrected "new date()" to "new Date()"
                request.getDescription(false) // Corrected typo and added parameter
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
