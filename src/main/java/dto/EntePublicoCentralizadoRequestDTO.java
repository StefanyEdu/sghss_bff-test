package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntePublicoCentralizadoRequestDTO {
    private String emailCoop;
    private String codCoop;
    private String dataInicio;
    private Integer totalMeses;
    private String dataFim;
    private Integer idEstado;
    private String nomCidade;


}
