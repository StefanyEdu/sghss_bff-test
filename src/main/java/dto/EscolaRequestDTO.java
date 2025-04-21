package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EscolaRequestDTO {
    private String nomeFantasia;
    private String cnpj;
    private String inep;
    private String coop;
    private String razaoSocial;
    private String telefone;
    private String email;
    private String estado;
    private Integer idEstado;
    private String cidade;
    private String bairro;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private Boolean procurarSemelhantes;
    private Integer tipoInstituicao;
    private Integer[] etapasEducacao;
    private Integer []modalidade;


}
