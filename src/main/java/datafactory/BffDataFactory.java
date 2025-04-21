package datafactory;

import dto.*;

public class BffDataFactory {

    public AgendaRequestDTO cadastroAgenda(String dataInicio, String dataFim, String mes,
                                           String medicoResponsavel,
                                           String recorrencia,
                                           String tipoExame) {
        return AgendaRequestDTO
                .builder()
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .mes(mes)
                .medicoResponsavel(medicoResponsavel)
                .recorrencia(recorrencia)
                .tipoExame(tipoExame)
                .build();
    }

    public LeitosRequestDTO cadastroLeitos(Integer quantidadeLeitos, String ala,
                                           Integer andar,
                                           String tipoLeito) {
        return LeitosRequestDTO
                .builder()
                .quantidadeLeitos(quantidadeLeitos)
                .ala(ala)
                .andar(andar)
                .tipoLeito(tipoLeito)
                .build();
    }

    public PacienteRequestDTO cadastroPaciente(String cpfPaciente,
                                               String nomePaciente,
                                               String telefone,
                                               String endereco,
                                               String convenio,
                                               String bairro,
                                               String alergias,
                                               String numero,
                                               String email,
                                               String cidade,
                                               String estado, String quadroClinico) {

        return PacienteRequestDTO
                .builder()
                .cpfPaciente(cpfPaciente)
                .nomePaciente(nomePaciente)
                .telefone(telefone)
                .endereco(endereco)
                .convenio(convenio)
                .bairro(bairro)
                .alergias(alergias)
                .numero(numero)
                .email(email)
                .cidade(cidade)
                .estado(estado)
                .quadroClinico(quadroClinico)
                .build();
    }

    public ProfissionalSaudeRequestDTO cadastroProfissionalSaude(String cpfCnpjProfissionalSaude,
                                                                 String especialidade,
                                                                 String CRM,
                                                                 String telefone,
                                                                 String endereco,
                                                                 String cidade,
                                                                 String estado,
                                                                 String email) {
        return ProfissionalSaudeRequestDTO
                .builder()
                .cpfCnpjProfissionalSaude(cpfCnpjProfissionalSaude)
                .especialidade(especialidade)
                .CRM(CRM)
                .telefone(telefone)
                .endereco(endereco)
                .cidade(cidade)
                .estado(estado)
                .email(email)
                .build();
    }

    public ProntuarioRequestDTO cadastroProntuarioPaciente(String relatosPaciente, String procedimentosIniciais,
                                                           String examesEncaminhados,
                                                           String tratamento) {
        return ProntuarioRequestDTO
                .builder()
                .relatosPaciente(relatosPaciente)
                .procedimentosIniciais(procedimentosIniciais)
                .examesEncaminhados(examesEncaminhados)
                .tratamento(tratamento)
                .build();

    }


    public ReceitaRequestDTO cadastroReceita( String prescricao,String data){
        return ReceitaRequestDTO
                .builder()
                .prescricao(prescricao)
                .data(data)
                .build();
    }


}
