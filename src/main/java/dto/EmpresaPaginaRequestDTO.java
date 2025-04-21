package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaPaginaRequestDTO {
    private String busca;
    private Integer[] estados;
    private String[] cidades;
    private String[] assessores;
}
