package com.test.backend.poliza.domain.prima;

import com.test.backend.poliza.domain.amparo.Amparo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "primas")
@Entity(name = "Prima")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Prima {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "codigo_amparo_id")
  private Amparo codigoAmparo;

  private Integer edadMinima;

  private Integer edadMaxima;

  private  double porcentajePrima;
}
