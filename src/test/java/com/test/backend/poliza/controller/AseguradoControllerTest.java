package com.test.backend.poliza.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.backend.poliza.domain.asegurado.DataAsegurado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AseguradoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testLiquidarPoliza_Success() throws Exception {
    DataAsegurado dataAsegurado = new DataAsegurado(2L, "51000003", 100000);

    String jsonRequest = objectMapper.writeValueAsString(dataAsegurado);

    mockMvc.perform(post("/api/asegurados/liquidar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.tipoIdentificacion").value(2))
      .andExpect(jsonPath("$.nroIdentificacion").value("51000003"))
      .andExpect(jsonPath("$.valorAsegurado").value(100000))
      .andExpect(jsonPath("$.liquidacion[0].codigoAmparo").value(1))
      .andExpect(jsonPath("$.liquidacion[0].nombre").value("Muerte accidental"))
      .andExpect(jsonPath("$.liquidacion[0].valorPrima").value(2010.00))
      .andExpect(jsonPath("$.valorTotal").value(45620.00));
  }

  @Test
  public void testLiquidarPoliza_ErrorMissingFields() throws Exception {
    DataAsegurado dataAsegurado = new DataAsegurado(1L, "1234567", 0);

    String jsonRequest = objectMapper.writeValueAsString(dataAsegurado);

    mockMvc.perform(post("/api/asegurados/liquidar")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
      .andExpect(status().isBadRequest());
  }
}

