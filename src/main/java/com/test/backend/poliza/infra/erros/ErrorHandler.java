package com.test.backend.poliza.infra.erros;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity tratarError404() {
    return ResponseEntity.notFound().build();
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
    var errores = e.getFieldErrors().stream()
      .map(DatosErrorValidacion::new).toList();
    return ResponseEntity.badRequest().body(errores);
  }
  @ExceptionHandler(IntegrityValidation.class)
  public ResponseEntity errorHandlerIntegrityValidation(Exception e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }
  @ExceptionHandler(IllegalArgument.class)
  public ResponseEntity errorIllegalArgumentException(Exception e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  private record DatosErrorValidacion(String field, String error) {
    public DatosErrorValidacion(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }
}
