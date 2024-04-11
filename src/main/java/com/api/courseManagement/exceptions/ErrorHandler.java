package com.api.courseManagement.exceptions;

import com.api.courseManagement.controllers.dtos.ErrorDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBadRequestError(MethodArgumentNotValidException ex) {
        List<FieldError> errorsList = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errorsList.stream().map(ErrorDataValidation::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleBadRequestError(HttpMessageNotReadableException ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(CourseFullException.class)
    public ResponseEntity<ErrorDTO> handleCourseFullError(CourseFullException ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(FiveYearsGraduationException.class)
    public ResponseEntity<ErrorDTO> handleFiveYearsGraduationError(FiveYearsGraduationException ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InsufficientExperienceException.class)
    public ResponseEntity<ErrorDTO> handleInsufficientExperienceError(InsufficientExperienceException ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<ErrorDTO> handleInvalidCpfError(InvalidCpfException ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotBilingualException.class)
    public ResponseEntity<ErrorDTO> handleNotBilingualError(NotBilingualException ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnderAgeException.class)
    public ResponseEntity<ErrorDTO> handleUnderAgeError(UnderAgeException ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidLocationException.class)
    public ResponseEntity<ErrorDTO> handleInvalidLocationError(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleInternalServerError(Exception ex) {
        ErrorDTO error = new ErrorDTO(ex.getClass().getName(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private record ErrorDataValidation(String field, String message){
        public ErrorDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
