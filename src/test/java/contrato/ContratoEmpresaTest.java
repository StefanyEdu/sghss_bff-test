package contrato;

import basetest.BaseDataFactory;
import basetest.EmpresaBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.EmpresaPaginaRequestDTO;
import dto.EmpresaRequestDTO;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorNomeDataRg;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class ContratoEmpresaTest extends BaseDataFactory {
    String tokenValido;
    EmpresaBaseTest empresaBaseTest = new EmpresaBaseTest();
    EmpresaRequestDTO empresaRequestDTO = new EmpresaRequestDTO();

    @BeforeTest(groups = "contrato-empresa")
    public void geraToken(){
        tokenValido = empresaBaseTest.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");
    }

    @TestCase(key = "ADM08-T1272",keyCycle = "ADM08-C1889")
    @Test(groups = "contrato-empresa",priority = 1,description = "Deve cadastrar empresa ")
    public void validarCadastroEmpresaComSucesso(){
        empresaRequestDTO = empresaDataFactory.cadastraEmpresa("Rubem Berta", "91250554", "Porto Alegre"
                , new GeradorNomeDataRg().geradorCnpj(false), "Rua Paulo Renato", "Predio 1", 23, "Empresa Automação" + new GeradorNomeDataRg().geradorNomes(),
                "440");
        empresaBaseTest.postCadastraEmpresa(tokenValido, empresaRequestDTO).statusCode(HttpStatus.SC_CREATED);
      empresaBaseTest.getConsultaEmpresaPorCnpj(tokenValido, empresaRequestDTO.getCnpjEmpresa())
                .statusCode(HttpStatus.SC_OK)
              .body(matchesJsonSchema(new File("src/test/resources/json_scherma/postCadastroEmpresa.json")));

    }

}
