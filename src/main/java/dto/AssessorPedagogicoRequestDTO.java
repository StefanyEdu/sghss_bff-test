package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessorPedagogicoRequestDTO {
    private String nome;
    private String cpf;
    private String email;
    private String celular;
    private Integer idEmpresa;
    private String[] cooperativas;
    private String[] cooperativasGestor;
    private String idUsuario;
    private Integer status;
    private String statusReq;
    private Integer formacaoAcademica;
    private String qualificacao;
    private String dataNascimento;
    private Integer[] programasEducacionais;

}
