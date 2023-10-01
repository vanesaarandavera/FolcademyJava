package com.example.vanesa.Exceptions.Handler;

import com.example.vanesa.Exceptions.Dtos.ErrorMessageDTO;
import com.example.vanesa.Exceptions.ExceptionKinds.UserBadRequestException;
import com.example.vanesa.Exceptions.ExceptionKinds.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody

    public ResponseEntity<ErrorMessageDTO> defaultErrorHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody

    public ResponseEntity<ErrorMessageDTO> notFoundHandler (HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserBadRequestException.class)
    @ResponseBody

    public ResponseEntity<ErrorMessageDTO> badRequestHandler (HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
