package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistratoAssessorDescentralizadoRequestDTO {
    private Integer idDistrato;
    private Integer idContrato;
    private boolean flgAtivo;
    private String htmlDistrato;
    private String dataDistrato;
    private Integer flgStatus;

}
