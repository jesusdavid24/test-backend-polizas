package com.test.backend.poliza.controller;

import com.test.backend.poliza.domain.asegurado.AseguradoResponse;
import com.test.backend.poliza.domain.asegurado.AseguradoService;
import com.test.backend.poliza.domain.asegurado.DataAsegurado;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/asegurados")
public class AseguradoController {

  private AseguradoService aseguradoService;

  public AseguradoController(AseguradoService aseguradoService) {
    this.aseguradoService = aseguradoService;
  }

  @PostMapping("/liquidar")
  @Transactional
  public ResponseEntity<AseguradoResponse> liquidarPoliza(@RequestBody DataAsegurado dataAsegurado) {
    AseguradoResponse aseguradoResponse = aseguradoService.liquidarPoliza(dataAsegurado);
    return ResponseEntity.ok(aseguradoResponse);
  }
}
