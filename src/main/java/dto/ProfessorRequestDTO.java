package dto;

import lombok.*;

import java.util.Collection;
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorRequestDTO {
    private String nome;
    private String cpf;
    private String email;
    private String celular;
    private Collection<ProfessorEscolaRequestDTO> escolas;
    private String idUsuario;
    private Integer status;
    private String statusReq;
    private String dataNascimento;


}
