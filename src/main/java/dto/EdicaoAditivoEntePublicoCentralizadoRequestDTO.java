package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EdicaoAditivoEntePublicoCentralizadoRequestDTO {
    private Integer totalMeses;
    private Integer idContrato;
    private String dataInicio;
    private String dataFim;
    private Integer versaoContrato;
    private String emailCoop;




}
