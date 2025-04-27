package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteAgendaRequestDTO {
    private String nomePaciente;
    private String nomeMedico;
    private String especialidade;
    private String horario;
    private String dataConsulta;
    private String exame;

}
