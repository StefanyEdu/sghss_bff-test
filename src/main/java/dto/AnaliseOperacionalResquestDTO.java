package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnaliseOperacionalResquestDTO {
    private Integer idContrato;
    private Integer versaoContrato;
    private String justificativa;
    private String htmlContrato;


}
