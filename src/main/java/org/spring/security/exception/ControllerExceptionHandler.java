package org.spring.security.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.security.security.CustomPermissionEvaluator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
  Logger logger= LoggerFactory.getLogger(ControllerExceptionHandler.class);

  @ExceptionHandler(value = {RuntimeException.class})
  public ResponseEntity<ErrorMessage> runTimeExceptionHandler(RuntimeException ex, WebRequest request) {

    ErrorMessage errorMessage=new ErrorMessage(500,new Date(),ex.getMessage(),ex.toString());
    logger.info("ControllerExceptionHandler : runTimeExceptionHandler() : {}",ex.toString());
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ErrorMessage> exceptionHandler(RuntimeException ex, WebRequest request) {

    ErrorMessage errorMessage=new ErrorMessage(500,new Date(),ex.getMessage(),ex.toString());
    logger.info("ControllerExceptionHandler : exceptionHandler() : {}",ex.toString());
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {FridayException.class})
  public ResponseEntity<ErrorMessage> fridayExceptionHandler(FridayException ex, WebRequest request) {
    logger.info("ControllerExceptionHandler : fridayExceptionHandler() : {}",ex.toString());
    ErrorMessage errorMessage=new ErrorMessage(ex.getStatusCode(),ex.getTimestamp(),ex.getMessage(),ex.getDescription());
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}