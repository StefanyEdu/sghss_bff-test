package datafactory;

import dto.AnaliseOperacionalResquestDTO;
import dto.AssessorCentralizadoRequestDTO;
import dto.AssessorVinculoRequestDTO;
import dto.BuscaContratoVigenteRequestDTO;

public class ContratoAssessorCentralizadoDataFactory {

    public AssessorCentralizadoRequestDTO cadastroContratoAssessorCentralizado(String codCoop, String[] cpfAssessores,
                                                                               String dataDistrato, String dataFim,String dataInicio, String emailCoop,
                                                                               Integer idAditivo, Integer idDistrato, Integer idContrato,
                                                                               Integer idEmpresa, String cnpjEmpresa, Integer versaoContrato,
                                                                               Integer totalMeses, Double valorAssessoria, Double valorFormacao,
                                                                               Double valorOutros, Double valorReuniao, Double valorReembolsoAlimentacao,
                                                                               Double valorReembolsoDeslocamento, Double valorReembolsoHospedagem,
                                                                               Double valorReembolsoOutros, Double valorReembolsoPedagio,
                                                                               Double reembolsoOutrosDescricao, Double reembolsoObservacoes) {
        return AssessorCentralizadoRequestDTO
                .builder()
                .codCoop(codCoop)
                .cpfAssessores(cpfAssessores)
                .dataDistrato(dataDistrato)
                .dataFim(dataFim)
                .dataInicio(dataInicio)
                .emailCoop(emailCoop)
                .idAditivo(idAditivo)
                .idDistrato(idDistrato)
                .idContrato(idContrato)
                .idEmpresa(idEmpresa)
                .cnpjEmpresa(cnpjEmpresa)
                .versaoContrato(versaoContrato)
                .totalMeses(totalMeses)
                .valorAssessoria(valorAssessoria)
                .valorFormacao(valorFormacao)
                .valorOutros(valorOutros)
                .valorReuniao(valorReuniao)
                .valorReembolsoAlimentacao(valorReembolsoAlimentacao)
                .valorReembolsoDeslocamento(valorReembolsoDeslocamento)
                .valorReembolsoHospedagem(valorReembolsoHospedagem)
                .valorReembolsoOutros(valorReembolsoOutros)
                .valorReembolsoPedagio(valorReembolsoPedagio)
                .reembolsoOutrosDescricao(reembolsoOutrosDescricao)
                .reembolsoObservacoes(reembolsoObservacoes)
                .build();
    }
    public BuscaContratoVigenteRequestDTO buscaContratoVigenteAssessorCentralizado(String codCop,String cpfAssessor,String data){
        return BuscaContratoVigenteRequestDTO
                .builder()
                .codCop(codCop)
                .cpfAssessor(cpfAssessor)
                .data(data)
                .build();

    }

    public AnaliseOperacionalResquestDTO analiseOperacionalAssessorCentralizado(Integer idContrato,Integer versaoContrato
                                                                                ,String justificativa,String htmlContrato){
        return AnaliseOperacionalResquestDTO
                .builder()
                .idContrato(idContrato)
                .versaoContrato(versaoContrato)
                .justificativa(justificativa)
                .htmlContrato(htmlContrato)
                .build();
    }
}
