
# API Liquidación de polizas

## Badges

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)

## Index

* [Badges](#badges)
* [Descripción](#descripción)
* [Características](#características)
* [API de referencia](#api-de-referencia)
* [Acceso al proyecto](#acceso-al-proyecto)
* [Stack tecnológico](#stack-tecnológico)
* [Tests](#tests)
* [Autores](#autores)
* [Licensia](#licensia)
* [Conclusión](#conclusión)

## Descripción

Este proyecto es una API para la liquidación de pólizas de seguros, que permite calcular el valor de las primas asociadas a distintos tipos de 
coberturas (amparos) según los datos de los asegurados. Desarrollado en Java utilizando Spring Boot, el sistema recibe los datos de un asegurado, 
incluyendo su tipo y número de identificación, y el valor asegurado, y devuelve un desglose de las primas correspondientes a las coberturas aplicables.

## Características

- Liquidación de Pólizas: Cálculo automático del valor de la prima para diferentes coberturas de la póliza.
- Validación de Entradas: Verifica que los campos obligatorios estén presentes, devolviendo errores si faltan datos.
- Códigos de Error Claros: Devuelve un código de estado HTTP 400 en caso de que falten campos en la solicitud o si los datos son inválidos.
- Amparos Configurables: El sistema maneja diferentes coberturas de póliza como muerte accidental, desmembración, auxilio funerario y renta vitalicia.

This API serves as the backbone for the foro_hub platform, ensuring secure and efficient management of user interactions and topic discussions.

## API de referencia

#### Post Liquidaciones

| Route                    | HTTP Verb | Description                                     |
|--------------------------|-----------|-------------------------------------------------|
| /api/asegurados/liquidar | POST      | Devuelve un json con la información del cliente |

1. Se le debe enviar un JSON de esta forma:

```json
{
  "tipoIdentificacion": "2",
  "nroIdentificacion": "51000003",
  "valorAsegurado": 100000
}
```
2. Y la API devolvera un JSON con la información validada:

```json
{
  "tipoIdentificacion": 2,
  "nroIdentificacion": "51000003",
  "valorAsegurado": 100000,
  "liquidacion": [
    {
      "codigoAmparo": 1,
      "nombre": "Muerte accidental",
      "valorPrima": 2010.00
    },
    {
      "codigoAmparo": 2,
      "nombre": "Desmembración",
      "valorPrima": 16040.00
    },
    {
      "codigoAmparo": 3,
      "nombre": "Auxilio funerario",
      "valorPrima": 14120.00
    },
    {
      "codigoAmparo": 4,
      "nombre": "Renta vitalicia",
      "valorPrima": 13450.00
    }
  ],
  "valorTotal": 45620.00
}
```
3. En caso de que el cliente no envie un dato se mostrara un error 400:

```json
{
  "field": "nroIdentificacion",
  "error": "El número de identificación no debe estar en blanco"
}
```

## Acceso al proyecto

1. Clonar el repositorio desde GitHub

```bash
  https://github.com/jesusdavid24/test-backend-polizas.git
```
2. Navegar el directorio del proyecto

```bash
  cd test-backend-polizas
```
3. Utilice Maven para compilar el proyecto y descargar las dependencias necesarias

```bash
  mvn clean install
```
4. Configura la aplicación:

```bash
Modifique el archivo application.properties en el directorio src/main/resources para adaptarlo 
entorno local. Esto incluye la configuración de la base de datos y otros ajustes necesarios
```

```bash
spring.datasource.url=jdbc:mysql://${DATASOURCE_HOST}/${DATASOURCE_NAME}
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.main.allow-bean-definition-overriding=true
server.error.include-stacktrace=never
spring.flyway.enabled=true
```
5. Tambien debe crear el archivo src/main/resources/application-test.properties. Esto se utiliza pata hacerle test al código.

```bash
spring.datasource.url=jdbc:mysql://${DATASOURCE_HOST}/polizas_test?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}
```

6. Ejecutar la aplicación

```bash
mvn spring-boot:run
```
7. Access the API

```bash
Una vez que la aplicación se está ejecutando, puede acceder a la API en http://localhost:8080.
Utilice un cliente API como Postman para interactuar con el endpoint de liquidación de polizas. 
```
8. Documentación de la API e interfaces de prueba

```bash
Springdoc y Swagger: El proyecto utiliza Springdoc y Swagger para la documentación de la API. 
Puede acceder a la interfaz de usuario Swagger para explorar y probar todos los puntos finales disponibles.

Abra su navegador y vaya a http://localhost:8080/swagger-ui.html para ver la interfaz Swagger 
de Swagger.

Esta interfaz le permite probar el endpoint directamente desde su navegador, 
lo que facilita la interacción con la API y la comprensión de su funcionalidad.
```
9. Te proporcionamos un Curl para que lo puedas probar directamente en postman:

```bash
curl -X 'POST' \
  'http://localhost:8080/api/asegurados/liquidar' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "tipoIdentificacion": 2,
  "nroIdentificacion": "51000003",
  "valorAsegurado": 1000000
}'
```

## Stack tecnológico

1. Java 17
2. Spring Boot 3
3. Maven
4. Spring Data JPA
5. H2 Database
6. BD MySQL
7. Springdoc and Swagger
8. JUnit y MockMvc para las pruebas unitarias
9. Flyway para las migraciones de la base de datos

## Tests
- Tests Unitarios: Se implementaron tests unitarios para verificar la funcionalidad de los servicios, asegurando que el cálculo de las primas y la lógica de negocio operen correctamente.
- Tests de Integración: Se realizaron tests de integración para los controladores, validando el correcto funcionamiento de los endpoints y asegurando que las respuestas se generen adecuadamente según los datos de entrada proporcionados.

## Autores

- [@jesusdavid24](https://github.com/jesusdavid24)

## Licensia

[MIT](https://choosealicense.com/licenses/mit/)

## Conclusión

Este proyecto proporciona una sólida API para la liquidación de pólizas de seguros, con capacidades de validación, cálculos precisos de primas, y documentada de forma interactiva con Swagger. Los tests unitarios y de integración garantizan 
la calidad y la fiabilidad del sistema.