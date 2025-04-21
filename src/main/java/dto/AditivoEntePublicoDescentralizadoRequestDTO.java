package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AditivoEntePublicoDescentralizadoRequestDTO {
    private Integer totalMeses;
    private String codCoop;
    private String dataInicio;
    private Integer idEstado;
    private String nomCidade;
    private String dataFim;
    private Integer idContrato;
    private String emailCoop;
    private Integer tipoContrato;
    private Integer versaoContrato;

}
