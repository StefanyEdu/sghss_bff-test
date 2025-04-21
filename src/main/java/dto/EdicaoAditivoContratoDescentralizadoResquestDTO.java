package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EdicaoAditivoContratoDescentralizadoResquestDTO {
    private Boolean flgAtivo;
    private Integer idContrato;
    private String reembolso;
    private Double valorAssessoria;
    private Double valorReuniao;
    private Double valorFormacao;
    private Double valorOutros;
    private String htmlAditivo;
    private String dataInicio;
    private String dataFim;
    private Integer totalMeses;
    private Integer flgStatus;
    private Double valorReembolsoHospedagem;
    private Double valorReembolsoAlimentacao;
    private Double valorReembolsoDeslocamento;
    private Double valorReembolsoPedagio;
    private Double valorReembolsoOutros;
    private String reembolsoOutrosDescricao;
    private String reembolsoObservacoes;
}
