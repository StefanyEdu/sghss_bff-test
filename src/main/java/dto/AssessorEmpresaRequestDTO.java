package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessorEmpresaRequestDTO {
    private Integer [] estados;
    private String[] cidades;
}
