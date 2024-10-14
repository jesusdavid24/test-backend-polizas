package com.test.backend.poliza.controller;

import com.test.backend.poliza.domain.asegurado.AseguradoResponse;
import com.test.backend.poliza.domain.asegurado.AseguradoService;
import com.test.backend.poliza.domain.asegurado.DataAsegurado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/asegurados")
@Tag(name = "Asegurados", description = "Endpoints relacionados con la liquidación de pólizas para asegurados")
public class AseguradoController {

  private AseguradoService aseguradoService;

  public AseguradoController(AseguradoService aseguradoService) {
    this.aseguradoService = aseguradoService;
  }

  @Operation(
    summary = "Liquidar póliza",
    description = "Calcula la liquidación de la póliza basada en los datos del asegurado. El valor total de la póliza " +
      "se calcula en función del valor asegurado y los amparos que aplican según la edad del asegurado."
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Liquidación calculada exitosamente",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = AseguradoResponse.class)) }),
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos. Faltan campos o tienen valores incorrectos.",
      content = @Content(mediaType = "application/json",
        examples = @ExampleObject(value = """
        [
          {
            "field": "tipoIdentificacion",
            "error": "no debe ser nulo"
          },
          {
            "field": "nroIdentificacion",
            "error": "no debe ser nulo"
          },
          {
            "field": "valorAsegurado",
            "error": "no debe ser nulo"
          }
        ]
      """))),
  })
  @PostMapping("/liquidar")
  @Transactional
  public ResponseEntity<AseguradoResponse> liquidarPoliza(@RequestBody @Valid DataAsegurado dataAsegurado) {
    AseguradoResponse aseguradoResponse = aseguradoService.liquidarPoliza(dataAsegurado);
    return ResponseEntity.ok(aseguradoResponse);
  }
}
