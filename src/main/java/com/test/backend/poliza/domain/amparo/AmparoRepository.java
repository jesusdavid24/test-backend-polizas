package com.test.backend.poliza.domain.amparo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmparoRepository extends JpaRepository<Amparo, Long> {
  @Query("SELECT a FROM Amparo a JOIN Prima p ON a.codigo = p.codigoAmparo.codigo WHERE :edad BETWEEN p.edadMinima AND p.edadMaxima")
  List<Amparo> findAmparosByEdad(@Param("edad") int edad);
}
