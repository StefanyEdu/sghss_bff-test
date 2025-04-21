package datafactory;

import dto.AditivoAssessorDescentralizadoRequestDTO;
import dto.AssessorDescentralizadoRequestDTO;
import dto.DistratoAssessorDescentralizadoRequestDTO;
import dto.EdicaoAditivoContratoDescentralizadoResquestDTO;

public class ContratoAssessorDescentralizadoDataFactory {
    public AssessorDescentralizadoRequestDTO cadastroContratoAssessorDescentralizado(Boolean flgAtivo,
                                                                                     Integer idContrato,
                                                                                     String codCoop,
                                                                                     String emailCoop,
                                                                                     String[] cpfAssessores,
                                                                                     Integer idEmpresa,
                                                                                     String cnpjEmpresa,
                                                                                     String reembolso,
                                                                                     Double valorAssessoria,
                                                                                     Double valorFormacao,
                                                                                     Double valorOutros,
                                                                                     Double valorReuniao,
                                                                                     String htmlContrato,
                                                                                     String dataInicio,
                                                                                     String dataFim,
                                                                                     Integer totalMeses,
                                                                                     Integer flgStaus,
                                                                                     Double valorReembolsoHospedagem,
                                                                                     Double valorReembolsoAlimentacao,
                                                                                     Double valorReembolsoDeslocamento,
                                                                                     Double valorReembolsoPedagio,
                                                                                     Double valorReembolsoOutros,
                                                                                     String reembolsoOutrosDescricao,
                                                                                     String reembolsoObservacoes,
                                                                                     Boolean flgAnaliseJuridica,
                                                                                     Integer[] programasEducacionais, Integer totalLimiteReembolso) {
        return AssessorDescentralizadoRequestDTO
                .builder()
                .flgAtivo(flgAtivo)
                .idContrato(idContrato)
                .codCoop(codCoop)
                .emailCoop(emailCoop)
                .cpfAssessores(cpfAssessores)
                .idEmpresa(idEmpresa)
                .cnpjEmpresa(cnpjEmpresa)
                .reembolso(reembolso)
                .valorAssessoria(valorAssessoria)
                .valorFormacao(valorFormacao)
                .valorOutros(valorOutros)
                .valorReuniao(valorReuniao)
                .htmlContrato(htmlContrato)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .totalMeses(totalMeses)
                .flgStaus(flgStaus)
                .valorReembolsoHospedagem(valorReembolsoHospedagem)
                .valorReembolsoAlimentacao(valorReembolsoAlimentacao)
                .valorReembolsoDeslocamento(valorReembolsoDeslocamento)
                .valorReembolsoPedagio(valorReembolsoPedagio)
                .valorReembolsoOutros(valorReembolsoOutros)
                .reembolsoOutrosDescricao(reembolsoOutrosDescricao)
                .reembolsoObservacoes(reembolsoObservacoes)
                .flgAnaliseJuridica(flgAnaliseJuridica)
                .programasEducacionais(programasEducacionais)
                .totalLimiteReembolso(totalLimiteReembolso)
                .build();
    }

    public AditivoAssessorDescentralizadoRequestDTO cadastroAditivoContratoAssessorDescentralizado(Boolean flgAtivo,
                                                                                                   Integer idContrato,
                                                                                                   String reembolso,
                                                                                                   Double valorAssessoria,
                                                                                                   Double valorReuniao,
                                                                                                   Double valorFormacao,
                                                                                                   Double valorOutros,
                                                                                                   String htmlAditivo,
                                                                                                   String dataInicio,
                                                                                                   String dataFim,
                                                                                                   Integer totalMeses,
                                                                                                   Integer flgStatus,
                                                                                                   Double valorReembolsoHospedagem,
                                                                                                   Double valorReembolsoAlimentacao,
                                                                                                   Double valorReembolsoDeslocamento,
                                                                                                   Double valorReembolsoPedagio,
                                                                                                   Double valorReembolsoOutros,
                                                                                                   String reembolsoOutrosDescricao,
                                                                                                   String reembolsoObservacoes) {
        return AditivoAssessorDescentralizadoRequestDTO.builder()
                .flgAtivo(flgAtivo)
                .idContrato(idContrato)
                .reembolso(reembolso)
                .valorAssessoria(valorAssessoria)
                .valorReuniao(valorReuniao)
                .valorFormacao(valorFormacao)
                .valorOutros(valorOutros)
                .htmlAditivo(htmlAditivo)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .totalMeses(totalMeses)
                .flgStatus(flgStatus)
                .valorReembolsoHospedagem(valorReembolsoHospedagem)
                .valorReembolsoAlimentacao(valorReembolsoAlimentacao)
                .valorReembolsoDeslocamento(valorReembolsoDeslocamento)
                .valorReembolsoPedagio(valorReembolsoPedagio)
                .valorReembolsoOutros(valorReembolsoOutros)
                .reembolsoOutrosDescricao(reembolsoOutrosDescricao)
                .reembolsoObservacoes(reembolsoObservacoes)
                .build();
    }

    public EdicaoAditivoContratoDescentralizadoResquestDTO edicaoAditivoContratoDescentralizado(Boolean flgAtivo,
                                                                                                Integer idContrato,
                                                                                                String reembolso,
                                                                                                Double valorAssessoria,
                                                                                                Double valorReuniao,
                                                                                                Double valorFormacao,
                                                                                                Double valorOutros,
                                                                                                String htmlAditivo,
                                                                                                String dataInicio,
                                                                                                String dataFim,
                                                                                                Integer totalMeses,
                                                                                                Integer flgStatus,
                                                                                                Double valorReembolsoHospedagem,
                                                                                                Double valorReembolsoAlimentacao,
                                                                                                Double valorReembolsoDeslocamento,
                                                                                                Double valorReembolsoPedagio,
                                                                                                Double valorReembolsoOutros,
                                                                                                String reembolsoOutrosDescricao,
                                                                                                String reembolsoObservacoes) {
        return EdicaoAditivoContratoDescentralizadoResquestDTO.builder()
                .flgAtivo(flgAtivo)
                .idContrato(idContrato)
                .reembolso(reembolso)
                .valorAssessoria(valorAssessoria)
                .valorReuniao(valorReuniao)
                .valorFormacao(valorFormacao)
                .valorOutros(valorOutros)
                .htmlAditivo(htmlAditivo)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .totalMeses(totalMeses)
                .flgStatus(flgStatus)
                .valorReembolsoHospedagem(valorReembolsoHospedagem)
                .valorReembolsoAlimentacao(valorReembolsoAlimentacao)
                .valorReembolsoDeslocamento(valorReembolsoDeslocamento)
                .valorReembolsoPedagio(valorReembolsoPedagio)
                .valorReembolsoOutros(valorReembolsoOutros)
                .reembolsoOutrosDescricao(reembolsoOutrosDescricao)
                .reembolsoObservacoes(reembolsoObservacoes)
                .build();
    }

    public DistratoAssessorDescentralizadoRequestDTO cadastroDistratoContratoAssessorDescentralizado(Integer idDistrato, Integer idContrato, boolean flgAtivo,
                                                                                                     String htmlDistrato, String dataDistrato,
                                                                                                     Integer flgStatus) {
        return DistratoAssessorDescentralizadoRequestDTO
                .builder()
                .idDistrato(idDistrato)
                .idContrato(idContrato)
                .flgAtivo(flgAtivo)
                .htmlDistrato(htmlDistrato)
                .dataDistrato(dataDistrato)
                .flgStatus(flgStatus)
                .build();
    }


}
