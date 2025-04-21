package datafactory;

import dto.AssessorEmpresaRequestDTO;
import dto.AssessorVinculoRequestDTO;
import dto.EmpresaPaginaRequestDTO;
import dto.EmpresaRequestDTO;

public class EmpresaDataFactory {
    public EmpresaRequestDTO cadastraEmpresa(String bairro, String cep, String cidade, String cnpjEmpresa, String complemento,
                                             String endereco, Integer idEstado, String nome, String numero) {
        return EmpresaRequestDTO
                .builder()
                .bairro(bairro)
                .cep(cep)
                .cidade(cidade)
                .cnpjEmpresa(cnpjEmpresa)
                .complemento(complemento)
                .endereco(endereco)
                .idEstado(idEstado)
                .nome(nome)
                .numero(numero)
                .build();
    }

    public EmpresaPaginaRequestDTO buscaPaginada(String busca,Integer[] estados,String[] cidades,String[] assessores){
        return EmpresaPaginaRequestDTO
                .builder()
                .busca(busca)
                .estados(estados)
                .cidades(cidades)
                .assessores(assessores)
                .build();

    }

    public AssessorVinculoRequestDTO vinculoAssessor(String[] assessoresNecessarios){
        return AssessorVinculoRequestDTO
                .builder()
                .assessoresNecessarios(assessoresNecessarios)
                .build();
    }
    public AssessorEmpresaRequestDTO buscaAssessorPorEstadoCidade(Integer [] estados,String[] cidades){
            return AssessorEmpresaRequestDTO
                    .builder()
                    .estados(estados)
                    .cidades(cidades)
                    .build();
    }
}
