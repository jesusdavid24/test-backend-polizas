package com.test.backend.poliza.domain.asegurado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataAsegurado(
  @NotNull
  Long tipoIdentificacion,
  @NotBlank
  String nroIdentificacion,
  @NotNull
  Integer valorAsegurado

) {
}
