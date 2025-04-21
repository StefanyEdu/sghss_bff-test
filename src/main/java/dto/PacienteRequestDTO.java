package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {
    private String cpfPaciente;
    private String nomePaciente;
    private String telefone;
    private String endereco;
    private String convenio;
    private String bairro;
    private String alergias;
    private String numero;
    private String email;
    private String cidade;
    private String estado;
    private String quadroClinico;

}
