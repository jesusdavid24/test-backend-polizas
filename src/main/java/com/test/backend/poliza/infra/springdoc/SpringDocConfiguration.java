package com.test.backend.poliza.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("API de Liquidación de Pólizas")
        .version("1.0")
        .description("**La API de Liquidación de Pólizas** permite a los usuarios calcular la liquidación de una póliza de seguro a partir de los datos de un asegurado. " +
          "El cálculo de la liquidación se realiza en base al valor asegurado y los amparos que aplican según la edad del asegurado.\n\n" +
          "El endpoint de la API recibe los siguientes datos obligatorios:\n" +
          "- **Tipo de identificación**: El tipo de documento del asegurado (1-CC, 2-CE).\n" +
          "- **Número de identificación**: El número de documento del asegurado.\n" +
          "- **Valor asegurado**: El valor monetario que se desea asegurar.\n\n" +
          "La API devuelve la liquidación en función de los amparos que apliquen según la edad del asegurado. Si no aplica ningún amparo, la respuesta no incluirá amparos.\n\n"))
      .components(new Components());
  }
}
