package com.rednap.finalproject.exception.handler;

import com.rednap.finalproject.exception.ItemNameConflictException;
import com.rednap.finalproject.exception.NoAdminPermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNameConflictException.class)
    public ResponseEntity itemNameConflictExceptionResponse(final ItemNameConflictException itemNameConflictException) {
        final String errorMessage = String.format("Item with name '%s' already exists", itemNameConflictException.getName());
        final ErrorMessage error = new ErrorMessage(errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(NoAdminPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void noAdminPermissionExceptionHandler(){}

    private record ErrorMessage(String message) {}

}
