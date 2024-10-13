package com.test.backend.poliza.domain.asegurado;

import com.test.backend.poliza.domain.tipoIdentificacion.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AseguradoRepository extends JpaRepository<Asegurado, Long> {
  @Query("SELECT a FROM Asegurado a WHERE a.tipoIdentificacion = :tipoIdentificacion AND a.nroIdentificacion = :nroIdentificacion")
  Asegurado findByIdentificacion(Optional<TipoIdentificacion> tipoIdentificacion, String nroIdentificacion);
}
