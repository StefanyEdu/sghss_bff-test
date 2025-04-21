package dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessorDescentralizadoRequestDTO {
   private Boolean flgAtivo;
   private Integer idContrato;
   private String codCoop;
   private String emailCoop;
   private String []cpfAssessores;
   private Integer idEmpresa;
   private String cnpjEmpresa;
   private String reembolso;
   private Double valorAssessoria;
   private Double valorFormacao;
   private Double valorOutros;
   private Double valorReuniao;
   private String htmlContrato;
   private String dataInicio ;
   private String dataFim;
   private Integer totalMeses;
   private Integer flgStaus;
   private Double valorReembolsoHospedagem;
   private Double valorReembolsoAlimentacao;
   private Double valorReembolsoDeslocamento;
   private Double valorReembolsoPedagio;
   private Double valorReembolsoOutros;
   private String reembolsoOutrosDescricao;
   private String reembolsoObservacoes;
   private Boolean flgAnaliseJuridica;
   private Integer[] programasEducacionais;
   private Integer totalLimiteReembolso;

}
