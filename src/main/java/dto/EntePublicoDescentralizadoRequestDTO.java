package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntePublicoDescentralizadoRequestDTO {

  private String codCoop;
  private String dataFim;
  private String dataInicio;
  private String emailCoop;
  private Boolean flgAnaliseJuridica;
  private Integer idEstado;
  private String nomCidade;
  private Integer[] programasEducacionais;
  private Integer totalMeses;

}
