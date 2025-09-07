package com.khoipd8.accounts.exception;

import com.khoipd8.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle {@link CustomerAlreadyExistsException} by returning an error response.
     *
     * @param exception the exception
     * @param webRequest the web request
     * @return the error response
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(
            CustomerAlreadyExistsException exception,
            WebRequest webRequest
    ) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()

        );
        return new ResponseEntity<>(
                errorResponseDto,
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Handle {@link ResourceNotFoundException} by returning an error response with a 404 NOT FOUND status.
     *
     * @param exception the exception
     * @param webRequest the web request
     * @return the error response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()

        );
        return new ResponseEntity<>(
                errorResponseDto,
                HttpStatus.NOT_FOUND
        );
    }
}
