package com.test.backend.poliza.domain.amparo;

import java.math.BigDecimal;

public record AmparoResponse(
  int codigoAmparo,
  String nombre,
  BigDecimal valorPrima
) {
}
