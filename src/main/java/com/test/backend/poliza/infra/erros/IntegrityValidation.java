package com.test.backend.poliza.infra.erros;

public class IntegrityValidation extends RuntimeException {

  public IntegrityValidation(String s) {
    super(s);
  }
}
