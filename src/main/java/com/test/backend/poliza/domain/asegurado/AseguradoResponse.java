package com.test.backend.poliza.domain.asegurado;

import com.test.backend.poliza.domain.amparo.AmparoResponse;

import java.math.BigDecimal;
import java.util.List;

public record AseguradoResponse(
  Long tipoIdentificacion,
  String nroIdentificacion,
  int valorAsegurado,
  List<AmparoResponse> liquidacion,
  BigDecimal valorTotal
) {
}
