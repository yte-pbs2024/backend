package yte.pbs2024.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Object> handleUserExistException(UserExistException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

//    @ExceptionHandler(UserExistException.class)
//    public ResponseEntity<Object> handleUserExistException(UserExistException ex){
//        ErrorDetails errorDetails = new ErrorDetails();
//        errorDetails.setStatusCode(HttpStatus.OK.value());
//        errorDetails.setStatus(HttpStatus.OK.name());
//        errorDetails.setErrorMessage(ex.getMessage());
//        errorDetails.setTimeStamp(LocalDateTime.now());
//        return new ResponseEntity<>(errorDetails, HttpStatus.OK);
//    }

}