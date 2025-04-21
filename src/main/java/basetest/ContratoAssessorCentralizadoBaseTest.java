package basetest;

import enums.Servico;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static constans.AdministradorConstans.API_TOKEN;
import static io.restassured.RestAssured.given;
import static requestspecification.RequestSpecificationFactory.requestSpecification;
import static requestspecification.RequestSpecificationFactory.responseSpecification;

public class ContratoAssessorCentralizadoBaseTest {
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

    public ValidatableResponse postCadastroContratoAssessorCentralizado(String token, AssessorCentralizadoRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_CONTRATO_ASSESSOR_CENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postBuscaContratoVigente(String token, BuscaContratoVigenteRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .body(body)
                .when()
                .post(ENPOINT_POST_BUSCA_CONTRATO_ASSESSOR_VIGENTE )
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse postAnaliseOperacionalContratoAssessorCentralizado(String token, AnaliseOperacionalResquestDTO body) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", token)
                .body(body)
                .when()
                .post(ENPOINT_POST_ANALISE_OPERACIONAL_ASSESSOR_CENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getBuscaContratoGenerico(String token,Integer id,Integer versaoContrato){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("id",id)
                .pathParam("versaoContrato",versaoContrato)
                .when()
                .get(ENPOINT_GET_BUSCA_CONTRATO_GENERICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getListaLogContratoEmAnaliseOperacional(String token,Integer id,Integer versaoContrato){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization",token)
                .pathParam("id",id)
                .pathParam("versaoContrato",versaoContrato)
                .when()
                .post(ENPOINT_POST_BUSCA_CONTRATO_ASSESSOR_VIGENTE )
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postContratoAssinado(String token, Integer idContrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idContrato", idContrato)
                .when()
                .post(ENPOINT_POST_UPLOAD_DOCUMENTO_ASSINADO_ASSESSOR_CENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse postContratoAssinadoAditivo(String token, Integer idAditivo, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idAditivo", idAditivo)
                .when()
                .post(ENPOINT_POST_UPLOAD_DOCUMENTO_ASSINADO_ADITIVO_CENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse postContratoAssinadoDistrato(String token, Integer idDistrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idDistrato", idDistrato)
                .when()
                .post(ENPOINT_POST_UPLOAD_DOCUMENTO_ASSINADOO_DISTRATO_CENTRALIZAD0)
                .then()
                .spec(responseSpecification());
    }



    }







