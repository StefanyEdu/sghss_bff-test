package basetest;

import dto.*;
import enums.Servico;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static constans.AdministradorConstans.API_TOKEN;
import static constans.ProfissionalSaudeConstans.*;
import static io.restassured.RestAssured.given;
import static requestspecification.RequestSpecificationFactory.requestSpecification;
import static requestspecification.RequestSpecificationFactory.responseSpecification;

public class ContratoAssessoDescentralizadoBaseTest {
    public ValidatableResponse token(String grant_type, String username, String password, String scope){
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

    public ValidatableResponse postCadastroContratoAssessorDescentralizado(String token, AssessorDescentralizadoRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_CONTRATO_ASSESSOR_DESCENTRALIZADOS)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse putEdicaoContratoAssessor(String token,AssessorDescentralizadoRequestDTO body,Integer id){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .pathParam("id",id)
                .body(body)
                .when()
                .put(ENPOINT_PUT_EDICAO_CONTRATO_ASSESSOR_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse postCadastroAditivoContratoAssessor(String token,AditivoAssessorDescentralizadoRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_ADITIVO_ASSESSOR_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse putEdicaoAditivoContratoAssessor(String token, EdicaoAditivoContratoDescentralizadoResquestDTO body, Integer id){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .pathParam("id",id)
                .body(body)
                .when()
                .put(ENPOINT_PUT_EDICAO_ADITIVO_ASSESSOR_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroDistratoContratoAssessor(String token,DistratoAssessorDescentralizadoRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_DISTRATO_ASSESSOR_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse putEdicaoDistratoContratoAssessor(String token,DistratoAssessorDescentralizadoRequestDTO body, Integer id){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .pathParam("id",id)
                .body(body)
                .when()
                .put(ENPOINT_PUT_EDICAO_DISTRATO_ASSESSOR_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse postConsultaContratoVigente(String token, BuscaContratoVigenteRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENPOINT_POST_BUSCA_CONTRATO_VIGENTE)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadContratoAssinadoAssessor(String token, Integer idContrato, File documento){
        RequestSpecification spec = given()
            .spec(requestSpecification(Servico.PUFV_CONTRATO))
            .contentType("multipart/form-data")
                .header("Authorization","Bearer "+token)
            .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idContrato", idContrato)
            .when()
                .post(ENPOINT_POST_UPLOAD_ARQUIVO_ASSINADO_CONTRATO_ASSESSOR_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());

    }

    public ValidatableResponse postUploadContratoAditivoAssessor(String token, Integer idAditivo, File documento){
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization","Bearer "+token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idAditivo", idAditivo)
                .when()
                .post(ENPOINT_POST_UPLOAD_ARQUIVO_ASSINADO_ADITIVO_ASSESSOR_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());

    }
    public ValidatableResponse postContratoAssinadoDistrato(String token, Integer idDistrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization","Bearer "+token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idDistrato", idDistrato)
                .when()
                .post(ENPOINT_POST_UPLOAD_ARQUIVO_ASSINADO_DISTRATO_ASSESSOR_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse getConsultaContratoVigente(String token,Integer id,Integer versaoContrato){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .pathParam("id",id)
                .pathParam("versaoContrato",versaoContrato)
                .when()
                .get(ENPOINT_GET_CONSULTA_CONTRATO_VIGENTE)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse deleteDocumentoAssinadoContrato(String token,Integer idContrato){
            return given()
                    .spec(requestSpecification(Servico.PUFV_CONTRATO))
                    .header("Authorization","Bearer "+token)
                    .pathParam("idContrato",idContrato)
                    .when()
                    .delete(ENPOINT_DELETE_DOCUMENTO_CONTRATO)
                    .then()
                    .spec(responseSpecification());
    }
    public ValidatableResponse deleteDocumentoAssinadoAditivoDoContrato(String token,Integer idAditivo){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .pathParam("idAditivo",idAditivo)
                .when()
                .delete(ENPOINT_DELETE_DOCUMENTO_ADITIVO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse deleteDocumentoAssinadoDistratoDoContrato(String token,Integer idDistrato){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization","Bearer "+token)
                .pathParam("idDistrato",idDistrato)
                .when()
                .delete(ENPOINT_DELETE_DOCUMENTO_DISTRATO)
                .then()
                .spec(responseSpecification());
    }


}
