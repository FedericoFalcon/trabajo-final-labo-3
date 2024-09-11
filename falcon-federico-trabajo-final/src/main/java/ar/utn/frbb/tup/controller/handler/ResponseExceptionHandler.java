package ar.utn.frbb.tup.controller.handler;

import ar.utn.frbb.tup.persistence.exception.*;
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
            return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = {ProfesorNotFoundException.class})
    protected ResponseEntity<Object> handleProfesorNotFoundException(
            ProfesorNotFoundException ex, WebRequest request) {
            String exceptionMessage = ex.getMessage();
            CustomApiError error = new CustomApiError();
            error.setErrorMessage(exceptionMessage);
            return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {MateriaAlreadyExistsException.class})
    protected ResponseEntity<Object> handleMateriaAlreadyExistsException(
            MateriaAlreadyExistsException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = {MateriaNotFoundException.class})
    protected ResponseEntity<Object> handleMateriaNotFoundException(
            MateriaNotFoundException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler (value = {AlumnoAlreadyExistsException.class})
    protected ResponseEntity<Object> handleAlumnoAlreadyExistsException(
            AlumnoAlreadyExistsException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler (value = {AlumnoNotFoundException.class})
    protected ResponseEntity<Object> AlumnoNotFoundException(
            AlumnoNotFoundException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler (value = {CorrelatividadException.class})
    protected ResponseEntity<Object> CorrelatividadException(
            CorrelatividadException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
