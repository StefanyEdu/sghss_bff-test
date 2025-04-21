package contrato;

import basetest.BaseDataFactory;
import basetest.ContratoEntePublicoDescentralizadoBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.EntePublicoDescentralizadoRequestDTO;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class ContratoEntePublicoDescentralizadoTest extends BaseDataFactory {

    String tokenValido;
    ContratoEntePublicoDescentralizadoBaseTest contratoEntePublicoDescentralizado = new ContratoEntePublicoDescentralizadoBaseTest();
    EntePublicoDescentralizadoRequestDTO entePublicoDescentralizadoRequestDTO = new EntePublicoDescentralizadoRequestDTO();
    Integer idContrato;

    @BeforeTest(groups = "contrato-ente-publico-descentralizado")
    public void baseTest() {
        tokenValido = contratoEntePublicoDescentralizado.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");

    }

    @TestCase(key = "ADM08-T1142",keyCycle = "ADM08-C1893")
    @Test(groups = "contrato-ente-publico-descentralizado",priority = 1,description = "")
    public void validarContratoVigenteComSucessoTest(){
        entePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.cadastroEntePublico(
                "0101",
                "2024-01-20",
                "2023-01-20",
                "stefany_eduarda@sicredi.com.br",
                true,
                23,
                "GRAMADO",
                new Integer[]{0, 1},
                null);
        idContrato = contratoEntePublicoDescentralizado.postCadastraContratoEntePublico(tokenValido,
                        entePublicoDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getInt("id");

        contratoEntePublicoDescentralizado.postContratoAssinado(tokenValido, idContrato,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

        contratoEntePublicoDescentralizado.
                postUploadExtratoContratoEntePublico(tokenValido, idContrato,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

        contratoEntePublicoDescentralizado.
                postUploadPlanoTrabalhoContratoEntePublico(tokenValido, idContrato,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

        contratoEntePublicoDescentralizado.getConsultaContrato(tokenValido, idContrato, 1)
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchema(new File("src/test/resources/json_scherma/getContratoVigenteEntePublicoDescentralizado.json")));

    }


    @AfterClass(groups = "contrato-ente-publico-descentralizado")
        public void limpaContrato(){
        contratoEntePublicoDescentralizado.deleteDocumentosContratoEntePublico(tokenValido,idContrato)
                .statusCode(HttpStatus.SC_OK);
       contratoEntePublicoDescentralizado.putDesativaContrato(tokenValido,idContrato,1)
               .statusCode(HttpStatus.SC_OK);
    }






































}
