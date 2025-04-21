package basetest;

import dto.AditivoEntePublicoDescentralizadoRequestDTO;
import dto.DistratoEntePublicoRequestDTO;
import dto.EdicaoAditivoEntePublicoDescentralizadoDTO;
import dto.EntePublicoDescentralizadoRequestDTO;
import enums.Servico;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static constans.AdministradorConstans.API_TOKEN;
import static constans.ProfissionalSaudeConstans.*;
import static io.restassured.RestAssured.given;
import static requestspecification.RequestSpecificationFactory.requestSpecification;
import static requestspecification.RequestSpecificationFactory.responseSpecification;

public class ContratoEntePublicoDescentralizadoBaseTest {
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


    public ValidatableResponse postCadastraContratoEntePublico(String token, EntePublicoDescentralizadoRequestDTO body) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_CONTRATO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse putEditaContratoEntePublico(String token, EntePublicoDescentralizadoRequestDTO body, Integer idContrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idContrato", idContrato)
                .body(body)
                .when()
                .put(ENPOINT_PUT_EDICAO_CONTRATO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }


    public ValidatableResponse postContratoAssinado(String token, Integer idContrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idContrato", idContrato)
                .when()
                .post(ENPOINT_POST_DOCUMENTO_ASSINADO_CONTRATO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadExtratoContratoEntePublico(String token, Integer idContrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idContrato", idContrato)
                .when()
                .post(ENPOINT_POST_ULPOAD_EXTRATO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadPlanoTrabalhoContratoEntePublico(String token, Integer idContrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idContrato", idContrato)
                .when()
                .post(ENPOINT_POST_UPLOAD_PLANO_TRABALHO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getConsultaContrato(String token, Integer id, Integer versaoContrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("id", id)
                .pathParam("versaoContrato", versaoContrato)
                .when()
                .get(ENPOINT_GET_BUSCA_CONTRATOS_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroAditivoContratoEntePublico(String token, AditivoEntePublicoDescentralizadoRequestDTO body) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_ADITIVO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse putEditarAditivoContratoEntePublico(String token, EdicaoAditivoEntePublicoDescentralizadoDTO body, Integer idAditivo) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idAditivo", idAditivo)
                .body(body)
                .when()
                .put(ENPOINT_PUT_EDICAO_ADITIVO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadDocumentoAssinadoAditivoContratoEntePublico(String token, Integer idAditivo, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idAditivo", idAditivo)
                .when()
                .post(ENPOINT_POST_UPLOAD_DOCUMENTO_ASSINADO_ADITIVO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadPlanoTrabalhoAditivoDoContratoEntePublico(String token, Integer idAditivo, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idAditivo", idAditivo)
                .when()
                .post(ENPOINT_POST_UPLOAD_PLANO_DE_TRABALHO_ADITIVO_ENTE_PUBLICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadExtratoAditivoDoContratoEntePublico(String token, Integer idAditivo, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idAditivo", idAditivo)
                .when()
                .post(ENPOINT_POST_UPLOAD_EXTRATO_ADITIVO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroDistratoContratoEntePublico(String token, DistratoEntePublicoRequestDTO body) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_DISTRATO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse putEditarDistratoContratoEntePublico(String token, DistratoEntePublicoRequestDTO body, Integer idDistrato) {
        return given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .header("Authorization", "Bearer " + token)
                .pathParam("idDistrato", idDistrato)
                .body(body)
                .when()
                .put(ENPOINT_PUT_EDICAO_DISTRATO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadDocumentoAssinadoDistratoContratoEntePublico(String token, Integer idDistrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idDistrato", idDistrato)
                .when()
                .post(ENPOINT_POST_DOCUMENTO_ASSINADO_DISTRATO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadPlanoTrabalhoDistratoDoContratoEntePublico(String token, Integer idDistrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idDistrato", idDistrato)
                .when()
                .post(ENPOINT_POST_PLANO_TRABALHO_DISTRATO_ENTE_PUBLICO_DESCENTRALIZADO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postUploadExtratoDistratoDoContratoEntePublico(String token, Integer idDistrato, File documento) {
        RequestSpecification spec = given()
                .spec(requestSpecification(Servico.PUFV_CONTRATO))
                .contentType("multipart/form-data")
                .header("Authorization", "Bearer " + token)
                .multiPart("file", documento, "application/pdf");

        return spec
                .pathParam("idDistrato", idDistrato)
                .when()
                .post(ENPOINT_POST_EXTRATO_DISTRATO_ENTE_PUBLICO_DESCENTRALIZADO)
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








































