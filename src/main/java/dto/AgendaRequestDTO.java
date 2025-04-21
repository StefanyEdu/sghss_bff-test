package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaRequestDTO {
    String dataInicio;
    String dataFim;
    String mes;
    String medicoResponsavel;
    String recorrencia;
    String tipoExame;
}
