package funcional;

import basetest.BaseDataFactory;
import basetest.ContratoEntePublicoCentralizadoBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.*;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;

import static org.hamcrest.Matchers.*;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalContratoEntePublicoCentralizadoComErroTest extends BaseDataFactory {

    String tokenValido;
    Integer idContrato;
    ContratoEntePublicoCentralizadoBaseTest contratoEntePublicoCentralizado = new ContratoEntePublicoCentralizadoBaseTest();
    EntePublicoCentralizadoRequestDTO entePublicoCentralizadoRequestDTO = new EntePublicoCentralizadoRequestDTO();
    AnaliseOperacionalRequestEntePublicoCentralizadoDTO analiseOperacionalRequestEntePublicoCentralizadoDTO = new AnaliseOperacionalRequestEntePublicoCentralizadoDTO();
    AditivoEntePublicoCentralizadoRequestDTO aditivoEntePublicoCentralizadoRequestDTO = new AditivoEntePublicoCentralizadoRequestDTO();
    Integer idAditivo;
    Integer idDistrato;
    DistratoEntePublicoRequestDTO distratoEntePublicoRequestDTO = new DistratoEntePublicoRequestDTO();
    EdicaoAditivoEntePublicoDescentralizadoDTO edicaoAditivoEntePublicoDescentralizadoDTO = new EdicaoAditivoEntePublicoDescentralizadoDTO();

    @BeforeTest(groups = "funcional-ente-publico-centralizado-Com-Erro")
    public void baseTest() {
        tokenValido = contratoEntePublicoCentralizado.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");

    }


    @TestCase(key = "ADM08-T1323", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Erro", priority = 1)
    public void validarCadastroContratoEntePublicoCentralizadoComErroTest() {
        entePublicoCentralizadoRequestDTO = contratoEntePublicoCentralizadoDataFactory
                .cadastroContratoEntePublicoCentralizado("stefany_eduarda@sicredi.com.br",
                        "0101",
                        null,
                        null,
                        null,
                        null,
                        null);
        contratoEntePublicoCentralizado.postCadastraContratoEntePublico(tokenValido, entePublicoCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", is("Problema com dados da requisição."))
                .body("details.message", hasItem("Data final não foi informada."))
                .body("details.message",hasItem("Data de início não foi informada."))
                .body("details.message", hasItem("A Cidade não foi informada."))
                .body("details.message", hasItem("O Estado não foi informado."));

    }

    @TestCase(key = "ADM08-T1324", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Erro", priority = 2)
    public void validarCadastroContratoEntePublicoCentralizadoAditivoComErroTest() {
        entePublicoCentralizadoRequestDTO = contratoEntePublicoCentralizadoDataFactory
                .cadastroContratoEntePublicoCentralizado("stefany_eduarda@sicredi.com.br",
                        "0101",
                        "2023-02-05",
                        null,
                        "2024-03-02",
                        23,
                        "ESTEIO");
        idContrato = contratoEntePublicoCentralizado.postCadastraContratoEntePublico(tokenValido, entePublicoCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getInt("id");

        aditivoEntePublicoCentralizadoRequestDTO = contratoEntePublicoCentralizadoDataFactory.
                cadastroAditivo(null,
                        "0101",
                        "2023-02-05",
                        23,
                        "ESTEIO",
                        "2026-02-03",
                        idContrato,
                        "stefany_eduarda@sicredi.com.br",
                        2,
                        2);
      contratoEntePublicoCentralizado.postCadastroAditivoContratoEntePublico(tokenValido,
                        aditivoEntePublicoCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
              .body("message",is("Não é possível criar aditivo para um contrato ainda não assinado."));

        contratoEntePublicoCentralizado.putDesativaContrato(tokenValido, idContrato, 1)
                .statusCode(HttpStatus.SC_OK);


    }


    @TestCase(key = "ADM08-T1325", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Erro", priority = 3)
    public void validarCadastroContratoEntePublicoCentralizadoComSucessoTest() {
        entePublicoCentralizadoRequestDTO = contratoEntePublicoCentralizadoDataFactory
                .cadastroContratoEntePublicoCentralizado("stefany_eduarda@sicredi.com.br",
                        "0101",
                        "2023-02-05",
                        null,
                        "2024-03-02",
                        23,
                        "ESTEIO");
        idContrato = contratoEntePublicoCentralizado.postCadastraContratoEntePublico(tokenValido, entePublicoCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getInt("id");

        distratoEntePublicoRequestDTO = contratoEntePublicoCentralizadoDataFactory.
                cadastroDistratoEntePublico(idContrato, new GeradorDatas().dataInicioContrato());
     contratoEntePublicoCentralizado.postCadastroDistratoContratoEntePublico(tokenValido,
                        distratoEntePublicoRequestDTO)
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
             .body("message",is("Não é possível gerar um distrato com data início posterior a data fim do contrato."));

        contratoEntePublicoCentralizado.putDesativaContrato(tokenValido, idContrato, 1)
                .statusCode(HttpStatus.SC_OK);


    }



}
