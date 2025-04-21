package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessorCentralizadoRequestDTO {
    private String codCoop;
    private String []cpfAssessores;
    private String dataDistrato;
    private String dataFim;
    private String dataInicio;
    private String emailCoop;
    private Integer idAditivo;
    private Integer idDistrato;
    private Integer idContrato;
    private Integer idEmpresa;
    private String cnpjEmpresa;
    private Integer versaoContrato;
    private Integer totalMeses;
    private Double valorAssessoria;
    private Double valorFormacao;
    private Double valorOutros;
    private Double valorReuniao;
    private Double valorReembolsoAlimentacao;
    private Double valorReembolsoDeslocamento;
    private Double valorReembolsoHospedagem;
    private Double valorReembolsoOutros;
    private Double valorReembolsoPedagio;
    private Double reembolsoOutrosDescricao;
    private Double reembolsoObservacoes;

}
