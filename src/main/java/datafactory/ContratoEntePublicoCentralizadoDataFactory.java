package datafactory;

import dto.*;

public class ContratoEntePublicoCentralizadoDataFactory {


    public EntePublicoCentralizadoRequestDTO cadastroContratoEntePublicoCentralizado(String emailCoop, String codCoop,
                                                                                     String dataInicio,
                                                                                     Integer totalMeses,
                                                                                     String dataFim,
                                                                                     Integer idEstado,
                                                                                     String nomCidade) {


        return EntePublicoCentralizadoRequestDTO
                .builder()
                .emailCoop(emailCoop)
                .codCoop(codCoop)
                .dataInicio(dataInicio)
                .totalMeses(totalMeses)
                .dataFim(dataFim)
                .idEstado(idEstado)
                .nomCidade(nomCidade)
                .build();

    }

    public AnaliseOperacionalRequestEntePublicoCentralizadoDTO analiseOperacional(String comentario, Integer versaoContrato) {
        return AnaliseOperacionalRequestEntePublicoCentralizadoDTO
                .builder()
                .comentario(comentario)
                .versaoContrato(versaoContrato)
                .build();

    }

    public AditivoEntePublicoCentralizadoRequestDTO cadastroAditivo(Integer totalMeses, String codCoop, String dataInicio,
                                                                    Integer idEstado, String nomCidade, String dataFim,
                                                                    Integer idContrato, String emailCoop,
                                                                    Integer tipoContrato, Integer versaoContrato) {

        return AditivoEntePublicoCentralizadoRequestDTO
                .builder()
                .totalMeses(totalMeses)
                .codCoop(codCoop)
                .dataInicio(dataInicio)
                .idEstado(idEstado)
                .nomCidade(nomCidade)
                .dataFim(dataFim)
                .idContrato(idContrato)
                .emailCoop(emailCoop)
                .tipoContrato(tipoContrato)
                .versaoContrato(versaoContrato)
                .build();

    }

    public DistratoEntePublicoRequestDTO cadastroDistratoEntePublico(Integer idContrato, String dataDistrato) {
        return DistratoEntePublicoRequestDTO
                .builder()
                .idContrato(idContrato)
                .dataDistrato(dataDistrato)
                .build();

    }

    public EdicaoAditivoEntePublicoDescentralizadoDTO edicaoAditivoEntePublicoDescentralizado(Integer totalMeses, Integer idContrato,
                                                                                              String dataInicio, String dataFim,
                                                                                              Integer versaoContrato, String emailCoop) {
        return EdicaoAditivoEntePublicoDescentralizadoDTO
                .builder()
                .totalMeses(totalMeses)
                .idContrato(idContrato)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .versaoContrato(versaoContrato)
                .emailCoop(emailCoop)
                .build();

    }


}
