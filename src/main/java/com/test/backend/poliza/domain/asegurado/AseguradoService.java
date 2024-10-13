package com.test.backend.poliza.domain.asegurado;

import com.test.backend.poliza.domain.amparo.Amparo;
import com.test.backend.poliza.domain.amparo.AmparoRepository;
import com.test.backend.poliza.domain.amparo.AmparoResponse;
import com.test.backend.poliza.domain.prima.Prima;
import com.test.backend.poliza.domain.prima.PrimaRepository;
import com.test.backend.poliza.domain.tipoIdentificacion.TipoIdentificacion;
import com.test.backend.poliza.domain.tipoIdentificacion.TipoIdentificacionRepository;
import com.test.backend.poliza.infra.erros.IntegrityValidation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AseguradoService {
  private final AseguradoRepository aseguradoRepository;
  private final AmparoRepository amparoRepository;
  private final PrimaRepository primaRepository;
  private final TipoIdentificacionRepository tipoIdentificacionRepository;

  public AseguradoService(AseguradoRepository aseguradoRepository, AmparoRepository amparoRepository, PrimaRepository primaRepository, TipoIdentificacionRepository tipoIdentificacionRepository) {
    this.aseguradoRepository = aseguradoRepository;
    this.amparoRepository = amparoRepository;
    this.primaRepository = primaRepository;
    this.tipoIdentificacionRepository = tipoIdentificacionRepository;
  }

  public AseguradoResponse liquidarPoliza(DataAsegurado dataAsegurado) {

    if (dataAsegurado.valorAsegurado() <= 0) {
      throw new IllegalArgumentException("Valor asegurado debe ser mayor a cero.");
    }

    Optional<TipoIdentificacion> tipoIdentificacion = tipoIdentificacionRepository.findById(
      dataAsegurado.tipoIdentificacion()
    );

    if (tipoIdentificacion == null) {
      throw new IntegrityValidation("El tipo de identificacion ingresado no existe");
    }

    Asegurado asegurado = aseguradoRepository.findByIdentificacion(
      tipoIdentificacion, String.valueOf(dataAsegurado.nroIdentificacion())
    );

    if (asegurado == null) {
      throw new IntegrityValidation("El asegurado no fue encontrado.");
    }

    int edad = calcularEdad(asegurado.getFechaNacimiento());

    List<Amparo> amparosAplicable = amparoRepository.findAmparosByEdad(edad);

    BigDecimal valorTotal = BigDecimal.ZERO;
    List<AmparoResponse> liquidacion = new ArrayList<>();

    for (Amparo amparo : amparosAplicable) {

      Prima prima = primaRepository.findByCodigoAmparo(amparo.getCodigo(), edad);

      BigDecimal porcentajePrima = BigDecimal.valueOf(prima.getPorcentajePrima());
      BigDecimal valorPrima = calcularPrima(dataAsegurado.valorAsegurado(), porcentajePrima);

      liquidacion.add(new AmparoResponse(amparo.getCodigo(), amparo.getNombre(), valorPrima));

      valorTotal = valorTotal.add(valorPrima);

    }

    return new AseguradoResponse(
      dataAsegurado.tipoIdentificacion(),
      dataAsegurado.nroIdentificacion(),
      dataAsegurado.valorAsegurado(),
      liquidacion,
      valorTotal
    );

  }
  private BigDecimal calcularPrima(int valorAsegurado, BigDecimal porcentajePrima) {
    return BigDecimal.valueOf(valorAsegurado).multiply(porcentajePrima);
  }

  private int calcularEdad(LocalDate fechaNacimiento) {
    return Period.between(fechaNacimiento, LocalDate.now()).getYears();
  }
}
