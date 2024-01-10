package com.project.productservice.ControllerAdvices;

import com.project.productservice.DTO.ArithmeticExceptionDTO;
import com.project.productservice.DTO.ExceptionDTO;
import com.project.productservice.Exception.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDTO> handleArithmeticException() {
        ArithmeticExceptionDTO arithmeticExceptionDTO = new ArithmeticExceptionDTO();
        arithmeticExceptionDTO.setMessage("Something went worng");
        return new ResponseEntity<>(arithmeticExceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDTO> productNotExistsException(ProductNotExistException productNotExistException) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setMessage(productNotExistException.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
