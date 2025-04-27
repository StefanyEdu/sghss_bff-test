package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalSaudeRequestDTO {
    private String nomeProfissional;
    private String cpfCnpjProfissionalSaude;
    private String especialidade;
    private String CRM;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String email;

}
