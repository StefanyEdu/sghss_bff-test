package basetest;

import dto.AssessorPedagogicoRequestDTO;
import enums.Servico;
import io.restassured.response.ValidatableResponse;

import static constans.AdministradorConstans.API_TOKEN;
import static constans.PacienteUserConstans.PUT_CADASTRAR_OU_ALTERAR_ASSESSOR_PEDAGOGICO;
import static io.restassured.RestAssured.given;
import static requestspecification.RequestSpecificationFactory.requestSpecification;
import static requestspecification.RequestSpecificationFactory.responseSpecification;

public class AssessorPedagogicoBaseTest {
    public ValidatableResponse putCadastraAssessor(String token, AssessorPedagogicoRequestDTO body){
        return given()
                .spec(requestSpecification(Servico.PUFV_USERS))
                .header("Authorization",token)
                .body(body)
                .when()
                .put(PUT_CADASTRAR_OU_ALTERAR_ASSESSOR_PEDAGOGICO)
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
