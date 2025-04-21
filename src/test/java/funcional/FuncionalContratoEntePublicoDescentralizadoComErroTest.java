package funcional;

import basetest.BaseDataFactory;
import basetest.ContratoEntePublicoDescentralizadoBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;

import java.io.File;

import static org.hamcrest.Matchers.is;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalContratoEntePublicoDescentralizadoComErroTest extends BaseDataFactory {

    String tokenValido;
    ContratoEntePublicoDescentralizadoBaseTest contratoEntePublicoDescentralizado = new ContratoEntePublicoDescentralizadoBaseTest();
    EntePublicoDescentralizadoRequestDTO entePublicoDescentralizadoRequestDTO = new EntePublicoDescentralizadoRequestDTO();
    Integer idContrato;
    AditivoEntePublicoDescentralizadoRequestDTO aditivoEntePublicoDescentralizadoRequestDTO = new AditivoEntePublicoDescentralizadoRequestDTO();

    @BeforeTest(groups = "funcional-ente-publico-descentralizado-Com-Erro")
    public void baseTest() {
        tokenValido = contratoEntePublicoDescentralizado.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");

    }

//    @TestCase(key = "ADM08-T1297",keyCycle = "ADM08-C1893")
//    @Test(groups = "funcional-ente-publico-descentralizado-Com-Erro",priority = 1,description = "")
//    public void validarCadastroContratoComErroTest() {
//        entePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.cadastroEntePublico(
//                "0109",
//                "2024-01-20",
//                "2023-01-20",
//                "stefany_eduarda@sicredi.com.br",
//                true,
//                null,
//                null,
//                new Integer[]{0, 1},
//                null);
//        contratoEntePublicoDescentralizado.postCadastraContratoEntePublico(tokenValido,
//                        entePublicoDescentralizadoRequestDTO)
//                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
//    }

        @TestCase(key = "ADM08-T1297",keyCycle = "ADM08-C1893")
        @Test(groups = "funcional-ente-publico-descentralizado-Com-Erro",priority = 2,description = "")
        public void validarCadastroContratoSemProgramasEducacionaisComErroTest() {
            entePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.cadastroEntePublico(
                    "0109",
                    "2024-01-20",
                    "2023-01-20",
                    "stefany_eduarda@sicredi.com.br",
                    true,
                    23,
                    "ESTEIO",
                    new Integer[]{null},
                    null);
            contratoEntePublicoDescentralizado.postCadastraContratoEntePublico(tokenValido,
                            entePublicoDescentralizadoRequestDTO)
                    .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
                    .body("message",is("Programa Educacional informado é inválido."));
        }

    @TestCase(key = "ADM08-T1297",keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Erro",priority = 3,description = "")
    public void validarCadastroContratoComContratoExistenteComErroTest() {
        entePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.cadastroEntePublico(
                "0109",
                "2024-01-20",
                "2023-01-20",
                "stefany_eduarda@sicredi.com.br",
                true,
                23,
                "ESTEIO",
                new Integer[]{0,1},
                null);
     idContrato=   contratoEntePublicoDescentralizado.postCadastraContratoEntePublico(tokenValido,
                        entePublicoDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
             .extract().response().jsonPath().getInt("id");

        entePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.cadastroEntePublico(
                "0109",
                "2024-01-20",
                "2023-01-20",
                "stefany_eduarda@sicredi.com.br",
                true,
                23,
                "ESTEIO",
                new Integer[]{0,1},
                null);
        contratoEntePublicoDescentralizado.postCadastraContratoEntePublico(tokenValido,
                        entePublicoDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
                .body("message",is("Já existe um contrato de Ente Público para cooperativa e cidade selecionados."));

        contratoEntePublicoDescentralizado.putDesativaContrato(tokenValido,idContrato,1)
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1298",keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Erro",priority = 4,description = "")
    public void validarCadastroAditivoSemContratoVigenteComErroTest() {
        entePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.cadastroEntePublico(
                "0109",
                "2024-01-20",
                "2023-01-20",
                "stefany_eduarda@sicredi.com.br",
                true,
                23,
                "ESTEIO",
                new Integer[]{0,1},
                null);
        idContrato=   contratoEntePublicoDescentralizado.postCadastraContratoEntePublico(tokenValido,
                        entePublicoDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getInt("id");

        aditivoEntePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.
                cadastroAditivoEntePublico(
                        null,
                        "0109",

                        "2023-01-20",
                        23,
                        "ESTEIO",
                        "2024-03-02",
                        idContrato,
                        "stefany_eduarda@terceros.sicredi.com.br",
                        2,
                        2);
     contratoEntePublicoDescentralizado.postCadastroAditivoContratoEntePublico(tokenValido,
                        aditivoEntePublicoDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
             .body("message",is("Não é possível criar aditivo para um contrato ainda não assinado."));
        contratoEntePublicoDescentralizado.putDesativaContrato(tokenValido,idContrato,1)
                .statusCode(HttpStatus.SC_OK);

    }


    @TestCase(key = "ADM08-T1131", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Erro", priority = 1, description = "Cadastro do contrato ente publico descentralizado")
    public void validarCadastroContratoEntePublicoDescentralizadoComErroTest() {
        entePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.cadastroEntePublico(
                "0155",
                new GeradorDatas().dataInicioContrato(),
                new GeradorDatas().dataInicioContrato(),
                "stefany_eduarda@sicredi.com.br",
                true,
                23,
                "GRAVATAI",
                new Integer[]{0, 1},
                null);
        idContrato = contratoEntePublicoDescentralizado.postCadastraContratoEntePublico(tokenValido,
                        entePublicoDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getInt("id");

        entePublicoDescentralizadoRequestDTO= contratoEntePublicoDescentralizadoDataFactory.cadastroEntePublico(
                "0155",
                new GeradorDatas().dataFimContrato(),
                new GeradorDatas().dataInicioContrato(),
                "stefany_eduarda@terceiro.sicredi.com.br",
                true,
                23,
                "GRAVATAI",
                new Integer[]{0,1},
                12);
        contratoEntePublicoDescentralizado.putEditaContratoEntePublico(tokenValido,entePublicoDescentralizadoRequestDTO,idContrato)
                .statusCode(HttpStatus.SC_OK);

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
                .body("codCoop", CoreMatchers.is(entePublicoDescentralizadoRequestDTO.getCodCoop()))
                .body("nomCidade", CoreMatchers.is("GRAVATAI"))
                .body("idContrato", CoreMatchers.is(idContrato));

        aditivoEntePublicoDescentralizadoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.
                cadastroAditivoEntePublico(
                        null,
                        "0155",
                        new GeradorDatas().dataInicioContrato(),
                        23,
                        "GRAVATAI",
                        new GeradorDatas().dataInicioContrato(),
                        idContrato,
                        "stefany_eduarda@terceros.sicredi.com.br",
                        2,
                        2);
   Integer     idAditivo = contratoEntePublicoDescentralizado.postCadastroAditivoContratoEntePublico(tokenValido,
                        aditivoEntePublicoDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getInt("id");

       contratoEntePublicoDescentralizado.postUploadExtratoAditivoDoContratoEntePublico(tokenValido, idAditivo,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
               .body("message",is("\"Não é possível realizar esta operação pois o aditivo não está assinado."));

       contratoEntePublicoDescentralizado.postUploadDocumentoAssinadoAditivoContratoEntePublico(tokenValido, idAditivo,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

        contratoEntePublicoDescentralizado.deleteDocumentosAditivoContratoEntePublico(tokenValido,idAditivo)
                .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoDescentralizado.putDesativaContrato(tokenValido,idAditivo,2)
                .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoDescentralizado.deleteDocumentosContratoEntePublico(tokenValido,idContrato)
                .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoDescentralizado.putDesativaContrato(tokenValido,idContrato,1)
                .statusCode(HttpStatus.SC_OK);



    }

}
