package datafactory;

import dto.AditivoEntePublicoDescentralizadoRequestDTO;
import dto.DistratoEntePublicoRequestDTO;
import dto.EdicaoAditivoEntePublicoDescentralizadoDTO;
import dto.EntePublicoDescentralizadoRequestDTO;

public class ContratoEntePublicoDescentralizadoDataFactory {
    public EntePublicoDescentralizadoRequestDTO cadastroEntePublico(String codCoop, String dataFim, String dataInicio,
                                                                    String emailCoop, Boolean flgAnaliseJuridica,
                                                                    Integer idEstado, String nomCidade,
                                                                    Integer[] programasEducacionais, Integer totalMeses) {
        return EntePublicoDescentralizadoRequestDTO
                .builder()
                .codCoop(codCoop)
                .dataFim(dataFim)
                .dataInicio(dataInicio)
                .emailCoop(emailCoop)
                .flgAnaliseJuridica(flgAnaliseJuridica)
                .idEstado(idEstado)
                .nomCidade(nomCidade)
                .programasEducacionais(programasEducacionais)
                .totalMeses(totalMeses)
                .build();

    }

    public AditivoEntePublicoDescentralizadoRequestDTO cadastroAditivoEntePublico(Integer totalMeses, String codCoop, String dataInicio,
                                                                                  Integer idEstado, String nomCidade, String dataFim,
                                                                                  Integer idContrato, String emailCoop, Integer tipoContrato,
                                                                                  Integer versaoContrato) {
        return AditivoEntePublicoDescentralizadoRequestDTO
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

    public EdicaoAditivoEntePublicoDescentralizadoDTO editarAditivoContratoEntePublico(Integer totalMeses, Integer idContrato,
                                                                                       String dataInicio, String dataFim,
                                                                                       Integer versaoContrato,
                                                                                       String emailCoop) {
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

    public DistratoEntePublicoRequestDTO cadastroDistratoEntePublico(Integer idContrato,String dataDistrato){
        return DistratoEntePublicoRequestDTO
                .builder()
                .idContrato(idContrato)
                .dataDistrato(dataDistrato)
                .build();

    }

}
