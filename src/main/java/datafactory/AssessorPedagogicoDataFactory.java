package datafactory;

import dto.AssessorPedagogicoRequestDTO;

public class AssessorPedagogicoDataFactory {
    public AssessorPedagogicoRequestDTO cadastroAssessor(String nome, String cpf, String email, String celular, Integer idEmpresa,
                                                         String[] cooperativas, String[] cooperativasGestor, String idUsuario,
                                                         Integer status, String statusReq, Integer formacaoAcademica,
                                                         String qualificacao, String dataNascimento, Integer[] programasEducacionais) {
        return AssessorPedagogicoRequestDTO
                .builder()
                .nome(nome)
                .cpf(cpf)
                .email(email)
                .celular(celular)
                .idEmpresa(idEmpresa)
                .cooperativas(cooperativas)
                .cooperativasGestor(cooperativasGestor)
                .idUsuario(idUsuario)
                .status(status)
                .statusReq(statusReq)
                .formacaoAcademica(formacaoAcademica)
                .qualificacao(qualificacao)
                .dataNascimento(dataNascimento)
                .programasEducacionais(programasEducacionais)
                .build();
    }


}
