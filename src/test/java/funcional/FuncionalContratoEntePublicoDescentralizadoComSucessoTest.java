package funcional;

import basetest.BaseDataFactory;
import basetest.ContratoEntePublicoDescentralizadoBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;


@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalContratoEntePublicoDescentralizadoComSucessoTest extends BaseDataFactory {

    String tokenValido;
    ContratoEntePublicoDescentralizadoBaseTest contratoEntePublicoDescentralizado = new ContratoEntePublicoDescentralizadoBaseTest();
    EntePublicoDescentralizadoRequestDTO entePublicoDescentralizadoRequestDTO = new EntePublicoDescentralizadoRequestDTO();
    Integer idContrato;
    AditivoEntePublicoDescentralizadoRequestDTO aditivoEntePublicoDescentralizadoRequestDTO = new AditivoEntePublicoDescentralizadoRequestDTO();
    EdicaoAditivoEntePublicoDescentralizadoDTO edicaoAditivoEntePublicoDescentralizadoDTO = new EdicaoAditivoEntePublicoDescentralizadoDTO();
    Integer idAditivo;
    Integer idDistrato;
    DistratoEntePublicoRequestDTO distratoEntePublicoRequestDTO = new DistratoEntePublicoRequestDTO();


    @BeforeTest(groups = "funcional-ente-publico-descentralizado-Com-Sucesso")
    public void baseTest() {
        tokenValido = contratoEntePublicoDescentralizado.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");

    }

    @TestCase(key = "ADM08-T1131", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 1, description = "Cadastro do contrato ente publico descentralizado")
    public void validarCadastroContratoEntePublicoDescentralizadoComSucessoTest() {
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

    }

    @TestCase(key = "ADM08-T1132",keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso",priority = 2,description = "Edição do contrato ente publico")
    public void validarEditarContratoEntePublicoDescentralizadoComSucessoTest(){
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

      }


    @TestCase(key = "ADM08-T1139", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 3, description = "")
    public void validarCadastroDocumentoContratoComSucessoTest() {
        contratoEntePublicoDescentralizado.postContratoAssinado(tokenValido, idContrato,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);


    }

    @TestCase(key = "ADM08-T1138", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 4, description = "")
    public void validarCadastrarExtratoContratoEntePublicoDescentralizadoComSucessoTest() {
        contratoEntePublicoDescentralizado.
                postUploadExtratoContratoEntePublico(tokenValido, idContrato,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }


    @TestCase(key = "ADM08-T1129", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 5, description = "")
    public void validarCadastrarOPlanoTrabalhoComSucessoTest() {
        contratoEntePublicoDescentralizado.
                postUploadPlanoTrabalhoContratoEntePublico(tokenValido, idContrato,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1137", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 6, description = "")
    public void validarConsultaContratoAssinadoComSucessoTest() {
        contratoEntePublicoDescentralizado.getConsultaContrato(tokenValido, idContrato, 1)
                .statusCode(HttpStatus.SC_OK)
                .body("codCoop", is(entePublicoDescentralizadoRequestDTO.getCodCoop()))
                .body("nomCidade", is("GRAVATAI"))
                .body("idContrato", is(idContrato));

    }

    @TestCase(key = "ADM08-T1133", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 7, description = "")
    public void validarCadastrarAditivoDoContratoComSucessoTest() {
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
        idAditivo = contratoEntePublicoDescentralizado.postCadastroAditivoContratoEntePublico(tokenValido,
                        aditivoEntePublicoDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getInt("id");


    }

    @TestCase(key = "ADM08-T1140", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 8, description = "")
    public void validarEditarAditivoDoContratoComSucessoTest() {
        edicaoAditivoEntePublicoDescentralizadoDTO = contratoEntePublicoDescentralizadoDataFactory
                .editarAditivoContratoEntePublico(null, idContrato, new GeradorDatas().dataInicioContrato(),
                        new GeradorDatas().dataFimContrato(),
                        2,
                        "stefany_eduarda@sicredi.com.br");
        contratoEntePublicoDescentralizado.putEditarAditivoContratoEntePublico(tokenValido,
                        edicaoAditivoEntePublicoDescentralizadoDTO, idAditivo)
                .statusCode(HttpStatus.SC_OK);


    }

    @TestCase(key = "ADM08-T1135", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 9, description = "")
    public void validarDocumentoExtratoAditivoDoContratoComSucessoTest() {
        contratoEntePublicoDescentralizado.postUploadExtratoAditivoDoContratoEntePublico(tokenValido, idAditivo,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1139", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 10)
    public void validarDocumentoAssinadoAditivoComSucessoTest() {
                contratoEntePublicoDescentralizado.postUploadDocumentoAssinadoAditivoContratoEntePublico(tokenValido, idAditivo,
                                new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);


    }



    @TestCase(key = "ADM08-T1144", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 11, description = "")
    public void validarDocumentoPlanoDeTrabalhoAditivoDoContratoComSucessoTest() {
        contratoEntePublicoDescentralizado.postUploadPlanoTrabalhoAditivoDoContratoEntePublico(tokenValido, idAditivo,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1141", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 12, description = "")
    public void validarCadastroDistratoDoContratoEntePublicoComSucessoTest() {
        distratoEntePublicoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.
                cadastroDistratoEntePublico(idContrato, new GeradorDatas().dataInicioContrato());
        idDistrato = contratoEntePublicoDescentralizado.postCadastroDistratoContratoEntePublico(tokenValido,
                        distratoEntePublicoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getInt("id");

    }

    @TestCase(key = "ADM08-T1145", keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 13, description = "")
    public void validarEdicaoDistratoDoContratoEntePublicoComSucessoTest() {
        distratoEntePublicoRequestDTO = contratoEntePublicoDescentralizadoDataFactory.cadastroDistratoEntePublico(idContrato, new GeradorDatas().dataInicioContrato());
        contratoEntePublicoDescentralizado.putEditarDistratoContratoEntePublico(tokenValido, distratoEntePublicoRequestDTO, idDistrato)
                .statusCode(HttpStatus.SC_OK);

    }

    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 14, description = "")
    public void validarDocumentoAssinadoDistratoDoContratoEntePublicoComSucessoTest() {
        contratoEntePublicoDescentralizado.
                postUploadDocumentoAssinadoDistratoContratoEntePublico(tokenValido, idDistrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1136",keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso",priority = 15,description = "")
    public void validarUploadExtratoDistratoDoContratoEntePublicoComSucessoTest(){
        contratoEntePublicoDescentralizado.postUploadExtratoDistratoDoContratoEntePublico(tokenValido,idDistrato,
                new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1134",keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso",priority = 16,description = "")
    public void validarUploadPlanoTrabalhoDistratoDoContratoEntePublicoComSucessoTest(){
        contratoEntePublicoDescentralizado.postUploadPlanoTrabalhoDistratoDoContratoEntePublico(tokenValido,idDistrato,
                        new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }


    @TestCase(key = "ADM08-T1294",keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso",priority = 17)
    public void validarExclusaoDocumentoAssinadoDistratoComSucesso(){
        contratoEntePublicoDescentralizado.deleteDocumentosDistratoContratoEntePublico(tokenValido,idDistrato)
                .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoDescentralizado.putDesativaContrato(tokenValido,idDistrato,3)
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1291",keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso",priority = 18)
    public void validarExclusaoDocumentoAssinadoAditivoComSucesso(){
        contratoEntePublicoDescentralizado.deleteDocumentosAditivoContratoEntePublico(tokenValido,idAditivo)
                        .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoDescentralizado.putDesativaContrato(tokenValido,idAditivo,2)
                .statusCode(HttpStatus.SC_OK);
    }


    @TestCase(key = "ADM08-T1292",keyCycle = "ADM08-C1893")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso",priority = 19)
    public void validarExclusaoDocumentoAssinadoComSucesso(){
        contratoEntePublicoDescentralizado.deleteDocumentosContratoEntePublico(tokenValido,idContrato)
                .statusCode(HttpStatus.SC_OK);

    }



    @AfterClass(groups = "funcional-ente-publico-descentralizado-Com-Sucesso")
    public void limpaContrato(){
        contratoEntePublicoDescentralizado.putDesativaContrato(tokenValido,idContrato,1)
                .statusCode(HttpStatus.SC_OK);

    }





}
