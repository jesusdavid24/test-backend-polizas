package com.test.backend.poliza.domain.prima;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrimaRepository extends JpaRepository<Prima, Long> {
  @Query("SELECT p FROM Prima p WHERE p.codigoAmparo.id = :codigoAmparoId AND :edad BETWEEN p.edadMinima AND p.edadMaxima")
  Prima findByCodigoAmparo(@Param("codigoAmparoId") Integer codigoAmparoId, @Param("edad") int edad);
}
