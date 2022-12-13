package paziylet_uz.utils.handler;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import paziylet_uz.utils.exception.AlreadyExistsException;
import paziylet_uz.utils.exception.NotFoundException;
import paziylet_uz.utils.exception.TypesInError;

import static paziylet_uz.payload.dao.MyResponse._ALREADY_EXISTS;
import static paziylet_uz.payload.dao.MyResponse._BAD_REQUEST;
import static paziylet_uz.payload.dao.MyResponse._ILLEGAL_TYPES;
import static paziylet_uz.payload.dao.MyResponse._NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AlreadyExistsException.class})
    public ResponseEntity<?> handleExists(AlreadyExistsException e, WebRequest request) {
        return _ALREADY_EXISTS.setMessage(e.getMessage()).handleResponse();
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> handleNotFound(NotFoundException e, WebRequest request) {
        return _NOT_FOUND.setMessage(e.getMessage()).handleResponse();
    }

    @ExceptionHandler(value = {TypesInError.class})
    public ResponseEntity<?> handleErrorWithTypes(TypesInError e, WebRequest request) {
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
        StringBuilder messages = new StringBuilder();
        methodArgumentNotValidException.getBindingResult()
                .getAllErrors().forEach(it -> messages.append(it.getDefaultMessage()).append(", "));
        return _BAD_REQUEST.setMessage(messages.substring(0, messages.length() - 2)).handleResponse();
    }
}
