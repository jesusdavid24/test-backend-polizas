package com.test.backend.poliza.domain.asegurado;

import com.test.backend.poliza.domain.amparo.AmparoResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Respuesta de la liquidación de póliza para un asegurado")
public record AseguradoResponse(

  @Schema(description = "Tipo de identificación del asegurado. Los valores posibles son: 1-CC, 2-CE.", example = "1", required = true)
  Long tipoIdentificacion,

  @Schema(description = "Número de identificación del asegurado", example = "79000001", required = true)
  String nroIdentificacion,

  @Schema(description = "Valor asegurado proporcionado por el cliente", example = "1000000", required = true)
  int valorAsegurado,

  @Schema(description = "Lista de amparos aplicables con el cálculo de la liquidación", required = true)
  List<AmparoResponse> liquidacion,

  @Schema(description = "Valor total de la liquidación, que es la suma de las primas aplicadas a los amparos", example = "23456.78", required = true)
  BigDecimal valorTotal

) {


}
