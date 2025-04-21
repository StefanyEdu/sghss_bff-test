package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorEscolaRequestDTO {
    private Integer idEscola;
    private Boolean gestor;
    private String nome;

}
