package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessorVinculoRequestDTO {
    private String[] assessoresNecessarios;
}
