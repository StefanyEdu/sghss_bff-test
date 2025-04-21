package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeitosRequestDTO {
    private Integer quantidadeLeitos;
    private String ala;
    private Integer andar;
    private String tipoLeito;

}
