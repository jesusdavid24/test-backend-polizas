package com.test.backend.poliza.domain.asegurado;

import com.test.backend.poliza.domain.sexo.Sexo;
import com.test.backend.poliza.domain.tipoIdentificacion.TipoIdentificacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "asegurados")
@Entity(name = "Asegurado")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Asegurado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tipo_identificacion_id")
  private TipoIdentificacion tipoIdentificacion;

  private  String nroIdentificacion;

  private String apellidos;

  private String nombres;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sexo_id")
  private Sexo sexo;

  private LocalDate fechaNacimiento;

  public Asegurado(
    TipoIdentificacion tipoIdentificacion,
    String nroIdentificacion,
    String apellidos,
    String nombres,
    Sexo sexo,
    LocalDate fechaNacimiento) {
    this.tipoIdentificacion = tipoIdentificacion;
    this.nroIdentificacion = nroIdentificacion;
    this.apellidos = apellidos;
    this.nombres = nombres;
    this.sexo = sexo;
    this.fechaNacimiento = fechaNacimiento;
  }

}
