package com.test.backend.poliza.domain.amparo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Respuesta que contiene los detalles de un amparo aplicable en la liquidación de la póliza")
public record AmparoResponse(

  @Schema(description = "Código del amparo. Los valores posibles son: 1-Muerte accidental, 2-Desmembración, 3-Auxilio funerario, 4-Renta vitalicia.", example = "1", required = true)
  int codigoAmparo,

  @Schema(description = "Nombre del amparo", example = "Muerte accidental", required = true)
  String nombre,

  @Schema(description = "Valor de la prima calculada para el amparo", example = "23456.78", required = true)
  BigDecimal valorPrima

) {
}
