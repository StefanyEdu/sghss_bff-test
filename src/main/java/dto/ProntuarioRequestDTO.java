package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProntuarioRequestDTO {
    private String relatosPaciente;
    private String procedimentosIniciais;
    private String examesEncaminhados;
    private String tratamento;
}
