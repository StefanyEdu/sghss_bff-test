package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaContratoVigenteRequestDTO {
    private String codCop;
    private String cpfAssessor;
    private String data;

}
