package paziylet_uz.utils.handler;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import paziylet_uz.utils.exception.AlreadyExistsException;
import paziylet_uz.utils.exception.NotFoundException;
import paziylet_uz.utils.exception.TypesInError;

import java.util.HashMap;
import java.util.Map;

import static paziylet_uz.payload.dao.MyResponse.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AlreadyExistsException.class})
    public ResponseEntity<?> handleCourseExists(AlreadyExistsException e, WebRequest request) {
        return _ALREADY_EXISTS.setMessage(e.getMessage()).handleResponse();
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> handleFileNotFound(NotFoundException e, WebRequest request) {
        return _NOT_FOUND.setMessage(e.getMessage()).handleResponse();
    }

    @ExceptionHandler(value = {TypesInError.class})
    public ResponseEntity<?> handleFileNotFound(TypesInError e, WebRequest request) {
        return _ILLEGAL_TYPES.setMessage(e.getMessage()).handleResponse();
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult()
                .getAllErrors().forEach(it -> errors.put(((FieldError) it).getField(), it.getDefaultMessage()));

        return _BAD_REQUEST.setMessage("Input types are in error").setBody(errors).handleResponse();
    }
}
