package com.test.backend.poliza.domain.asegurado;

import jakarta.validation.constraints.NotBlank;

public record DataAsegurado(
  @NotBlank
  String tipoIdentificacion,
  @NotBlank
  String nroIdentificacion,
  @NotBlank
  int valorAsegurado

) {
}
