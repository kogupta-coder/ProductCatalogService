package com.productCatalog.controllerAdvice;

import com.productCatalog.dtos.ExceptionDto;
import com.productCatalog.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductServiceExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleRuntimeException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went Wrong");
        exceptionDto.setResolutionMethod("Please try again later");

        return new ResponseEntity<>(
                exceptionDto,
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(){
        ExceptionDto exceptionDto = new ExceptionDto();

        exceptionDto.setMessage("Product Id is not found");
        exceptionDto.setResolutionMethod("Please try with available product Id");

        return new ResponseEntity<>(
                exceptionDto,
                HttpStatus.UNAUTHORIZED);
    }
}
