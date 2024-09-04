package ar.utn.frbb.tup.controller.handler;

import ar.utn.frbb.tup.persistence.exception.ProfesorAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProfesorAlreadyExistsException.class})
    protected ResponseEntity<Object> handleProfesorAlreadyExistsException(
            ProfesorAlreadyExistsException ex, WebRequest request) {
            String exceptionMessage = ex.getMessage();
            CustomApiError error = new CustomApiError();
            error.setErrorMessage(exceptionMessage);
            return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT, request);
    }
}
