package com.test.backend.poliza.asegurado;

import com.test.backend.poliza.domain.amparo.Amparo;
import com.test.backend.poliza.domain.amparo.AmparoRepository;
import com.test.backend.poliza.domain.asegurado.*;
import com.test.backend.poliza.domain.prima.Prima;
import com.test.backend.poliza.domain.prima.PrimaRepository;
import com.test.backend.poliza.domain.sexo.Sexo;
import com.test.backend.poliza.domain.tipoIdentificacion.TipoIdentificacion;
import com.test.backend.poliza.domain.tipoIdentificacion.TipoIdentificacionRepository;
import com.test.backend.poliza.infra.erros.IllegalArgument;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AseguradoServiceTest {

  @Mock
  private AseguradoRepository aseguradoRepository;

  @Mock
  private AmparoRepository amparoRepository;

  @Mock
  private PrimaRepository primaRepository;

  @Mock
  private TipoIdentificacionRepository tipoIdentificacionRepository;

  @Mock
  private Sexo sexo;

  @InjectMocks
  private AseguradoService aseguradoService;

  @Test
  public void testLiquidarPoliza_Success() {
    TipoIdentificacion tipoId = new TipoIdentificacion(2L, "CE");
    Sexo sexo1 = new Sexo(1L, "Masculino");
    Asegurado asegurado = new Asegurado(tipoId, "51000003", "Apellidos", "Nombres", sexo1, LocalDate.of(1980, 1, 10));

    Amparo amparo = new Amparo(1L, 1, "Muerte accidental");
    List<Amparo> amparos = Collections.singletonList(amparo);

    when(aseguradoRepository.findByIdentificacion(any(Optional.class), eq("51000003"))).thenReturn(asegurado);
    when(amparoRepository.findAmparosByEdad(anyInt())).thenReturn(amparos);

    Prima prima = new Prima();
    prima.setPorcentajePrima(0.02304);
    when(primaRepository.findByCodigoAmparo(anyInt(), anyInt())).thenReturn(prima);

    DataAsegurado request = new DataAsegurado(2L, "51000003", 100000);

    AseguradoResponse response = aseguradoService.liquidarPoliza(request);

    assertNotNull(response);
    assertEquals(1, response.liquidacion().size());
    assertEquals(0, response.valorTotal().compareTo(BigDecimal.valueOf(2304.00)));
  }

  @Test
  public void testLiquidarPoliza_ErrorValorAseguradoCero() {
    DataAsegurado request = new DataAsegurado(1L, "123456789", 0);

    IllegalArgument exception = assertThrows(IllegalArgument.class, () -> {
      aseguradoService.liquidarPoliza(request);
    });

    assertEquals("Valor asegurado debe ser mayor a cero.", exception.getMessage());
  }

}
