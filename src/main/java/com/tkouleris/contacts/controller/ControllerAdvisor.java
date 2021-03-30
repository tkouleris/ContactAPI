package com.tkouleris.contacts.controller;

import com.tkouleris.contacts.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception e) {
        String message = e.getMessage() != null ? e.getMessage():"Bad Request";
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(message);
        apiResponse.setData(null);

        return new ResponseEntity<>(apiResponse.getBodyResponse(), HttpStatus.BAD_REQUEST);
    }
}
