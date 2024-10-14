package com.test.backend.poliza.domain.asegurado;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Datos de entrada para calcular la liquidación de la póliza de un asegurado")
public record DataAsegurado(

  @Schema(description = "Tipo de identificación del asegurado. Los valores posibles son: 1-CC, 2-CE.", example = "1", required = true)
  @NotNull(message = "El tipo de identificación no debe ser nulo")
  Long tipoIdentificacion,

  @Schema(description = "Número de identificación del asegurado", example = "79000001", required = true)
  @NotBlank(message = "El número de identificación no debe estar en blanco")
  String nroIdentificacion,

  @Schema(description = "Valor asegurado que debe ser mayor a cero", example = "1000000", required = true)
  @NotNull(message = "El valor asegurado no debe ser nulo")
  Integer valorAsegurado

) {
  public DataAsegurado(Long tipoIdentificacion, String nroIdentificacion, Integer valorAsegurado) {
    this.tipoIdentificacion = tipoIdentificacion;
    this.nroIdentificacion = nroIdentificacion;
    this.valorAsegurado = valorAsegurado;
  }

}
