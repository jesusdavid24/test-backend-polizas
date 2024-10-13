package com.test.backend.poliza.domain.asegurado;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AseguradoRepository extends JpaRepository<Asegurado, Long> {
  @Query("SELECT a FROM Asegurado a WHERE a.nroIdentificacion = :nroIdentificacion")
  Asegurado findByNroIdentificacion(String nroIdentificacion);
}
