package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaRequestDTO {
    private String prescricao;
    private String data;
    private String assinatura;

}
