package dto;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseOperacionalRequestEntePublicoCentralizadoDTO {

    private String comentario;
    private Integer versaoContrato;


}