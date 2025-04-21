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

public class ContratoEntePublicoCentralizadoBaseTest {

    public ValidatableResponse token(String grant_type, String username, String password, String scope) {
        return given()
                .spec(requestSpecification(Servico.TOKEN))
                .auth().basic("9yD8fidC7UjKxkMDtNQcSiMYE8wa", "gYrIUfC7dpRoCCYPsRmHMp8xvI4a")
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("grant_type", grant_type)
                .formParam("username", username)
                .formParam("password", password)
                .formParam("scope", scope)
                .when()
                .post(API_TOKEN)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastraContratoEntePublico(String token, EntePublicoCentralizadoRequestDTO body) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_ENTE_PUBLICO_CENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse putDesativaContrato(String token, Integer id, Integer versaoContrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .pathParam("versaoContrato", versaoContrato)
                .when()
                .put(ENPOINT_PUT_ALTERA_STATUS_DO_CONTRATO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse putEditaContratoEntePublico(String token, Integer idContrato, EntePublicoCentralizadoRequestDTO body) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idContrato", idContrato)
                .body(body)
                .when()
                .put(ENPOINT_PUT_EDICAO_CONTRATO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse uploadDocumentoAnaliseOperacional(String token, Integer idContrato, File documento, Boolean flgFilePrincipal, Integer versaoContrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .queryParam("flgFilePrincipal", flgFilePrincipal)
                .queryParam("versaoContrato", versaoContrato)
                .pathParam("idContrato", idContrato)
                .multiPart("file", documento, "application/pdf")
                .post(ENPOINT_POST_ANALISE_OPERACIONAL_ENVIO_DOCUMENTO)
                .then()
                .spec(responseSpecification());
    }


    public ValidatableResponse analiseOperacionalComentarioEnvio(String token, Integer idContrato, AnaliseOperacionalRequestEntePublicoCentralizadoDTO boby) {

        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idContrato", idContrato)
                .body(boby)
                .when()
                .post(ENPOINT_POST_ANALISE_OPERACIONAL_ENVIO)
                .then()
                .spec(responseSpecification());


    }

    public ValidatableResponse analiseOperacionalRetornoAprovacao(String token,String comentario,Integer acaoAvalia,
                                                                  Boolean flgFilePrincipal,Integer versaoContrato,Integer idContrato, File documento){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf")
                .queryParam("comentario", comentario)
                .queryParam("acaoAvalia", acaoAvalia.toString())
                .queryParam("flgFilePrincipal",flgFilePrincipal)
                .queryParam("versaoContrato",versaoContrato)
                .pathParam("idContrato", idContrato)
                .when()
                .post(ENPOINT_POST_ANALISE_OPERACIONAL_ENVIO_RETORNO)
                .then()
                .spec(responseSpecification());


    }

    public ValidatableResponse uploadDocumentoContratoAssinado(String token, Integer idContrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idContrato", idContrato)
                .when()
                .post(ENPOINT_POST_O_DOCUMENTO_CONTRATO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse processoAssinaturaContrato(String token,Integer idContrato,Integer versaoContrato){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idContrato", idContrato)
                .pathParam("versaoContrato",versaoContrato)
                .when()
                .post(ENPOINT_POST_PROCESSO_ASSINATURA)
                .then()
                .spec(responseSpecification());

    }
    public ValidatableResponse uploadExtratoContratoAssinado(String token, Integer idContrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idContrato", idContrato)
                .when()
                .post(ENPOINT_POST_UPLOAD_EXTRATO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse uploadPlanoTrabalhoContratoAssinado(String token, Integer idContrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idContrato", idContrato)
                .when()
                .post(ENPOINT_POST_UPLOAD_PLANO_TRABALHO)
                .then()
                .spec(responseSpecification());
    }


    public ValidatableResponse getConsultaContratoEmAnaliseOperacional(String token, Integer idContrato, Integer versaoContrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idContrato", idContrato)
                .pathParam("versaoContrato", versaoContrato)
                .when()
                .get(ENPOINT_GET_CONSULTA_CONTRATOS_EM_ANALISE_OPERACIONAL)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroAditivoContratoEntePublico(String token, AditivoEntePublicoCentralizadoRequestDTO boby){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .body(boby)
                .when()
                .post(ENPOINT_POST_CADASTRO_ADITIVO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());

    }

    public ValidatableResponse putEdicaoAditivoContratoEntePublico(String token, EdicaoAditivoEntePublicoDescentralizadoDTO boby, Integer idAditivo){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idAditivo",idAditivo)
                .body(boby)
                .when()
                .put(ENPOINT_PUT_EDICAO_ADITIVO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());

    }

    public ValidatableResponse uploadDocumentoAditivoDoContratoAssinado(String token, Integer idAditivo, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idAditivo", idAditivo)
                .when()
                .post(ENPOINT_POST_DOCUMENTO_ASSINADO_ADITIVO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse uploadExtratoAditivoDoContratoAssinado(String token, Integer idAditivo, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idAditivo", idAditivo)
                .when()
                .post(ENPOINT_POST_UPLOAD_EXTRATO_ADITIVO_ENTE_PUBLICO )
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse uploadPlanoTrabalhoAditivoDoContratoAssinado(String token, Integer idAditivo, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idAditivo", idAditivo)
                .when()
                .post(ENPOINT_POST_PLANO_TRABALHO_ADITIVO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroDistratoContratoEntePublico(String token, DistratoEntePublicoRequestDTO boby){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .body(boby)
                .when()
                .post(ENPOINT_POST_CADASTRO_DISTRATO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());

    }

    public ValidatableResponse putEdicaoDistratoContratoEntePublico(String token, DistratoEntePublicoRequestDTO boby,Integer idDistrato){
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idDistrato",idDistrato)
                .body(boby)
                .when()
                .put(ENPOINT_PUT_EDICAO_DISTRATO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());

    }
    public ValidatableResponse uploadDocumentoDistratoDoContratoAssinado(String token, Integer idDistrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idDistrato", idDistrato)
                .when()
                .post(ENPOINT_POST_DOCUMENTO_ASSINADO_DISTRATO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse uploadExtratoDistratoDoContratoAssinado(String token, Integer idDistrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idDistrato", idDistrato)
                .when()
                .post(ENPOINT_POST_UPLOAD_EXTRATO_DISTRATO_ENTE_PUBLICO_CENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse uploadPlanoTrabalhoDistratoDoContratoAssinado(String token, Integer idDistrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idDistrato", idDistrato)
                .when()
                .post(ENPOINT_POST_UPLOAD_PLANO_DE_TRABALHO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getConsultaAditivoDistratoDoContrato(String token, Integer idContrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idContrato", idContrato)
                .when()
                .get(ENPOINT_GET_CONSULTA_CONTRATOS_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getConsultaContratoEntePublicoCentralizado(String token, Integer id,Integer versaoContrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .pathParam("versaoContrato",versaoContrato)
                .when()
                .get(ENPOINT_GET_CONSULTA_VERSAO_CONTRATOS)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse deleteDocumentosContratoEntePublico(String token, Integer idContrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idContrato", idContrato)
                .when()
                .delete(ENPOINT_DELETE_DOCUMENTOS_CONTRATO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse deleteDocumentosAditivoContratoEntePublico(String token, Integer idAditivo) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idAditivo", idAditivo)
                .when()
                .delete(ENPOINT_DELETE_DOCUMENTOS_ADITIVO_CONTRATO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse deleteDocumentosDistratoContratoEntePublico(String token, Integer idDistrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idDistrato", idDistrato)
                .when()
                .delete(ENPOINT_DELETE_DOCUMENTOS_DISTRATO_CONTRATO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }


}
