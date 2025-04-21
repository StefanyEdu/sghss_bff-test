package funcional;

import basetest.BaseDataFactory;
import basetest.EmpresaBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorNomeDataRg;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalEmpresaComErroTest extends BaseDataFactory {
    String tokenValido;
    EmpresaBaseTest empresaBaseTest = new EmpresaBaseTest();
    EmpresaRequestDTO empresaRequestDTO = new EmpresaRequestDTO();

    @BeforeTest(groups = "funcional-empresa-Com-Erro")
    public void geraToken(){
        tokenValido = empresaBaseTest.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");
    }

    @TestCase(key = "ADM08-T1271",keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Erro",priority = 1,description = "Deve cadastrar empresa ")
    public void validarCadastroEmpresaSemCnpj(){
        empresaRequestDTO = empresaDataFactory.cadastraEmpresa("Rubem Berta", "91250554", "Porto Alegre"
                , null, "Rua Paulo Renato", "Predio 1", 23, "Empresa Automação" + new GeradorNomeDataRg().geradorNomes(),
                "440");
        empresaBaseTest.postCadastraEmpresa(tokenValido, empresaRequestDTO).statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }
    @TestCase(key = "ADM08-T1271",keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Erro",priority = 2,description = "Deve cadastrar empresa ")
    public void validarCadastroEmpresaSemDadosEndereco(){
        empresaRequestDTO = empresaDataFactory.cadastraEmpresa("", "", ""
                ,  new GeradorNomeDataRg().geradorCnpj(false), "", "", null, "Empresa Automação" + new GeradorNomeDataRg().geradorNomes(),
                "");

        List<String> mensage = Arrays.asList(
                "Bairro deve ter no máximo 100 caracteres.",
                "Cidade deve ter no máximo 100 caracteres.",
                "Id do estado não pode ser vazio.",
                "Endereço deve ter no mínimo 1 no máximo 200 caracteres.",
                "CEP deve ter 8 caracteres."




        );
        empresaBaseTest.postCadastraEmpresa(tokenValido, empresaRequestDTO)
                 .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("details.message", hasItems(mensage.toArray()));
    }

}
