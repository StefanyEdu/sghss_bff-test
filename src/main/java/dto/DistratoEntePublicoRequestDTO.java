package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistratoEntePublicoRequestDTO {
    Integer idContrato;
    String dataDistrato;
}
