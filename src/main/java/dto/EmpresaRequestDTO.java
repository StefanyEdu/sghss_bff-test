package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequestDTO {
    private String bairro;
    private String cep;
    private String cidade;
    private String cnpjEmpresa;
    private String complemento;
    private String endereco;
    private Integer idEstado;
    private String nome;
    private String numero;

}
