package ntu.edu.sg.project3.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  // RESOURCE NOT FOUND EXCEPTION HANDLER
  @ExceptionHandler({ ProductNotFoundException.class, CategoryNotFoundException.class })
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
    logger.error(errorResponse.toString());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  // DELETION EXCEPTION HANDLER
  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
    ErrorResponse errorResponse = new ErrorResponse("Item does not exist.", LocalDateTime.now());
    logger.error(errorResponse.toString());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  // VALIDATION EXCEPTION HANDLER
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

    // Get the list of validation errors
    List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();

    // Create a Stringbuild to store all error messages
    StringBuilder sb = new StringBuilder();

    // Loop and append the errors
    for (ObjectError error : validationErrors) {
      sb.append(error.getDefaultMessage() + ". ");
    }

    ErrorResponse errorResponse = new ErrorResponse(sb.toString(), LocalDateTime.now());
    logger.error(errorResponse.toString());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

  }

  // GENERAL EXCEPTION HANDLER
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    // Add logging here
    // logger.error(ex.getMessage());
    // Return a generic error response so as not to leak sensitive information
    logger.error(ex.getMessage());
    ErrorResponse errorResponse = new ErrorResponse("Something went wrong", LocalDateTime.now());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
