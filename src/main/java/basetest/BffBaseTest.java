package basetest;

import dto.*;
import enums.Servico;
import io.restassured.response.ValidatableResponse;

import static constans.AdministradorConstans.*;
import static constans.PacienteUserConstans.*;
import static constans.ProfissionalSaudeConstans.*;
import static io.restassured.RestAssured.given;
import static requestspecification.RequestSpecificationFactory.requestSpecification;
import static requestspecification.RequestSpecificationFactory.responseSpecification;

public class BffBaseTest {
    public ValidatableResponse token(String grant_type, String username, String password, String scope){
        return given()
                .spec(requestSpecification(Servico.TOKEN))
                .auth().basic("9yD8fidC7UjKxkNDtNQcSiMYE8wa","gYrIUfC7dpRoCWYPsRmHMp8xvI9a")
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

    public ValidatableResponse postCadastroAgenda(String token,AgendaRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRA_AGENDA_MEDICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroPaciente(String token,PacienteRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRA_PACIENTE_PRONTO_ATENDIMENTO)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse postCadastroAgendaPaciente(String token, String cpfPaciente,PacienteAgendaRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .pathParam("cpfPaciente",cpfPaciente)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_AGENDA_PACIENTE)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroLeito(String token,LeitosRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENPOINT_POST_CADASTRO_LEITO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroAgendaExame(String token,AgendaRequestDTO boby){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(boby)
                .when()
                .post(ENPOINT_POST_CADASTRA_AGENDA_EXAME)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroPacienteAtendimento(String token,PacienteRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENDPOINT_POST_CADASTRO_PACIENTE)
                .then()
                .spec(responseSpecification());
    }
    public ValidatableResponse putCadastroPaciente(String token,PacienteRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .put(ENDPOINT_PUT_EDICAO_PACIENTE)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getConsultasAgendadasPorMedico(String token, Integer idMedico){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .pathParam("idMedico",idMedico)
                .when()
                .get(ENPOINT_GET_CONSULTA_AGANDADAS_MARCADAS_POR_MEDICO)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse getConsultasAgendadasPorPaciente(String token,String cpfPaciente){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .pathParam("cpfPaciente",cpfPaciente)
                .when()
                .get(ENPOINT_GET_CONSULTA_AGANDADAS_MARCADAS_POR_PACIENTE)
                .then()
                .spec(responseSpecification());
    }

    public ValidatableResponse postCadastroProfissionalMedico(String token, ProfissionalSaudeRequestDTO boby){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(boby)
                .when()
                .post(ENDPOINT_POST_CADASTRO_MEDICO)
                .then()
                .spec(responseSpecification());

    }

    public ValidatableResponse postCadastroProfissionalEnfermeira(String token, ProfissionalSaudeRequestDTO boby){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(boby)
                .when()
                .post(ENDPOINT_POST_CADASTRO_ENFERMEIRA)
                .then()
                .spec(responseSpecification());

    }

    public ValidatableResponse postCadastroReceita(String token, ReceitaRequestDTO boby){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(boby)
                .when()
                .post(ENPOINT_POST_CADASTRO_RECEITA)
                .then()
                .spec(responseSpecification());

    }



    public ValidatableResponse postCadastroProntuarioPaciente(String token, ProntuarioRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .body(body)
                .when()
                .post(ENDPOINT_POST_CADASTRO_PRONTUARIO_PACIENTE)
                .then()
                .spec(responseSpecification());

    }


    public ValidatableResponse getConsultasProfissionalSaude(String token,String cpfProfissional){
        return given()
                .spec(requestSpecification(Servico.SGHSS))
                .header("Authorization","Bearer "+token)
                .pathParam("cpfProfissional",cpfProfissional)
                .when()
                .get(ENDPOINT_GET_CONSULTA_TODOS_PROFISSIONAIS_SAUDE)
                .then()
                .spec(responseSpecification());
    }





}
