package com.pnu.lab.control.labcontrol.exception;

import com.pnu.lab.control.labcontrol.exception.dto.ErrorResponse;
import com.pnu.lab.control.labcontrol.exception.dto.RejectedFieldsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse bindExceptionHandler(BindException exception) {
        log.error("Bind exception", exception);
        ErrorResponse errorResponse = new ErrorResponse();
        BindingResult bindResult = exception.getBindingResult();

        bindResult.getFieldErrors().forEach(field -> errorResponse.getRejectedFields().add(new RejectedFieldsDto(field.getField(), field.getDefaultMessage())));

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        log.error("Cannot find Entity {} with ID {}.", exception.getType().getSimpleName(), exception.getId(), exception);
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
        log.error("Unknown exception", exception);
        return new ErrorResponse(exception.getMessage());
    }
}
