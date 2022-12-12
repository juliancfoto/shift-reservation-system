package com.dentalclinic.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
   private final static Logger LOGGER = Logger.getLogger(GlobalException.class);

   @ExceptionHandler(BadRequestException.class)
   private ResponseEntity<String> processBadRequeest(BadRequestException exception) {
      LOGGER.error(exception.getMessage());
      return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
   }

   @ExceptionHandler(ResourceNotFoundException.class)
   private ResponseEntity<String> processResourceNotFound(ResourceNotFoundException exception) {
      LOGGER.error(exception.getMessage());
      return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
   }
}