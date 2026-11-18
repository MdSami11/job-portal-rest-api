package com.example.jobportal.exception;


import com.example.jobportal.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;



@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException exception){


        ErrorResponse response = new ErrorResponse(

                        HttpStatus.NOT_FOUND.value(),

                        exception.getMessage(),

                        LocalDateTime.now()

                );


        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );

    }


    //General error = any unexpected error that you did not specifically handle.

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception){


        ErrorResponse response = new ErrorResponse(

                        HttpStatus.INTERNAL_SERVER_ERROR.value(),

                        "Something went wrong",

                        LocalDateTime.now()

                );


        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );

    }


    //Validation Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException exception){

        String message = exception.getBindingResult()
                        .getFieldErrors()
                        .get(0)
                        .getDefaultMessage();


        ErrorResponse response = new ErrorResponse(

                        400,

                        message,

                        LocalDateTime.now()

                );


        return ResponseEntity
                .badRequest()
                .body(response);

    }

}