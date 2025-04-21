package basetest;

import dto.AssessorEmpresaRequestDTO;
import dto.AssessorVinculoRequestDTO;
import dto.EmpresaPaginaRequestDTO;
import dto.EmpresaRequestDTO;
import enums.Servico;
import io.restassured.response.ValidatableResponse;


import static constans.AdministradorConstans.API_TOKEN;
import static constans.ProfissionalSaudeConstans.*;
import static io.restassured.RestAssured.given;
import static requestspecification.RequestSpecificationFactory.requestSpecification;
import static requestspecification.RequestSpecificationFactory.responseSpecification;

public class EmpresaBaseTest {
    public ValidatableResponse postCadastraEmpresa(String token, EmpresaRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_EMPRESA)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse getConsultaEmpresaPorCnpj(String token,String cnpj){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("cnpj",cnpj)
                .when()
                .get(ENPOINT_GET_CONSULTA_EMPRESA_POR_CNPJ)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getConsultaEmpresaPorId(String token, Integer id){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("id",id)
                .when()
                .get(ENPOINT_GET_CONSULTA_EMPRESA_POR_ID)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse putAlteraCadastroEmpresa(String token,Integer id, EmpresaRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("id",id)
                .body(body)
                .when()
                .put(ENPOINT_PUT_EDICAO_EMPRESA)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse deleteEmpresaPorId(String token,Integer id){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("id",id)
                .when()
                .delete(ENPOINT_DELETE_EMPRESA_POR_ID)
                .then()
                .spec(responseSpecification());

    }

    public ValidatableResponse postBuscaEmpresaPagina(String token, EmpresaPaginaRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .body(body)
                .when()
                .post(ENPOINT_POST_BUSCA_PAGINA)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postBuscaEmpresaPorCnpjEAdicionaNovosAssessores(String token, String cnpj, AssessorVinculoRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("cnpj",cnpj)
                .body(body)
                .when()
                .post(ENPOINT_POST_ADICAO_ASSESSOR_EMPRESA)
                .then()
                .spec(responseSpecification());

    }

    public ValidatableResponse postBuscaAssessorPorEstadoCidade(String token, AssessorEmpresaRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .body(body)
                .when()
                .post(ENPOINT_POST_BUSCA_ASSESSOR_VICULADOS_EMPRESAS)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getListaTodasEmpresas(String token){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .when()
                .get(ENPOINT_GET_TODAS_EMPRESAS)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getListaEmpresaPorCoop(String token,String coop){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("coop",coop)
                .when()
                .get(ENPOINT_GET_LISTA_EMPRESAS_POR_COOP)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getListaEmpresaPorAssessor(String token, String cpf){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("cpf",cpf)
                .when()
                .get(ENPOINT_GET_BUSCA_POR_CPFASSESSOR_EMPRESA)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse token(String grant_type,String username,String password,String scope){
        return given()
                .spec(requestSpecification(Servico.TOKEN))
                .auth().basic("9yD8fidC7UjKxkMDtNQcSiMYE8wa","gYrIUfC7dpRoCCYPsRmHMp8xvI4a")
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("grant_type",grant_type)
                .formParam("username",username)
                .formParam("password",password)
                .formParam("scope",scope)
                .when()
                .post(API_TOKEN)
                .then()
                .spec(responseSpecification());
    }



}
