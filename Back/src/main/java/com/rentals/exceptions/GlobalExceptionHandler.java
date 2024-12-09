package com.rentals.exceptions;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(BadRequestException.class)

//     public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException e) {
//         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                 .body(new ErrorResponse().setMessage(e.getMessage()));
//     }
// }