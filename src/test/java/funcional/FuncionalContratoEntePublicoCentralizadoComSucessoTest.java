package funcional;

import basetest.BaseDataFactory;
import basetest.ContratoEntePublicoCentralizadoBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.*;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;


@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalContratoEntePublicoCentralizadoComSucessoTest extends BaseDataFactory {

    String tokenValido;
    Integer idContrato;
    ContratoEntePublicoCentralizadoBaseTest contratoEntePublicoCentralizado = new ContratoEntePublicoCentralizadoBaseTest();
    EntePublicoCentralizadoRequestDTO entePublicoCentralizadoRequestDTO = new EntePublicoCentralizadoRequestDTO();
    AnaliseOperacionalRequestEntePublicoCentralizadoDTO analiseOperacionalRequestEntePublicoCentralizadoDTO = new AnaliseOperacionalRequestEntePublicoCentralizadoDTO();
    AditivoEntePublicoCentralizadoRequestDTO aditivoEntePublicoCentralizadoRequestDTO = new AditivoEntePublicoCentralizadoRequestDTO();
    Integer idAditivo;
    Integer idDistrato;
    DistratoEntePublicoRequestDTO distratoEntePublicoRequestDTO =  new DistratoEntePublicoRequestDTO();
    EdicaoAditivoEntePublicoDescentralizadoDTO edicaoAditivoEntePublicoDescentralizadoDTO = new EdicaoAditivoEntePublicoDescentralizadoDTO();

    @BeforeTest(groups = "funcional-ente-publico-centralizado-Com-Sucesso")
    public void baseTest() {
        tokenValido = contratoEntePublicoCentralizado.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");

    }


    @TestCase(key = "ADM08-T1112", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 1)
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


    }

    @TestCase(key = "ADM08-T1114",keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso",priority = 2)
    public void validarEdicaoContratoEntePublicoCentralizadoComSucessoTest(){
        entePublicoCentralizadoRequestDTO= contratoEntePublicoCentralizadoDataFactory
                .cadastroContratoEntePublicoCentralizado("stefany_eduarda@sicredi.com.br",
                        "0101",
                        "2023-02-05",
                        null,
                        "2024-03-02",
                        15,
                        "ESTEIO");

        contratoEntePublicoCentralizado.putEditaContratoEntePublico(tokenValido,idContrato,entePublicoCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1113", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 3)
    public void validarUploadDocumentoAnaliseOperacionalContratoEntePublicoCentralizadoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadDocumentoAnaliseOperacional(tokenValido, idContrato, new File("src/main/java/documents/teste.pdf"), false, 1)
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1113", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 4)
    public void validarAnaliseOperacionalContratoEntePublicoCentralizadoComSucessoTest() {
        analiseOperacionalRequestEntePublicoCentralizadoDTO = contratoEntePublicoCentralizadoDataFactory
                .analiseOperacional("teste deu certo", 1);
        contratoEntePublicoCentralizado
                .analiseOperacionalComentarioEnvio(tokenValido, idContrato, analiseOperacionalRequestEntePublicoCentralizadoDTO)
                .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoCentralizado.analiseOperacionalRetornoAprovacao(tokenValido,
                        "tudo certo", 1, true, 1, idContrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);


    }

    @TestCase(key = "ADM08-T1128", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 5)
    public void validarConsultaContratoEntePublicoCentralizadoComSucessoTest() {
        contratoEntePublicoCentralizado.getConsultaContratoEmAnaliseOperacional(tokenValido, idContrato, 1)
                .statusCode(HttpStatus.SC_OK)
                .body("nome", hasItems("Stefany Eduarda de Oliveira"))
                .body("acaoAvalia", hasItems("APROVADO"));
    }


    @TestCase(key = "ADM08-T1115", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 6)
    public void validarProcessoAssinaturaContratoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.processoAssinaturaContrato(tokenValido, idContrato, 1)
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1118", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 7)
    public void validarUploadContratoAssinadoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadDocumentoContratoAssinado(tokenValido, idContrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1116", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 8)
    public void validarUploadExtratoContratoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadExtratoContratoAssinado(tokenValido, idContrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1126", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 9)
    public void validarUploadPlanoDeTrabalhoContratoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadPlanoTrabalhoContratoAssinado(tokenValido, idContrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);


    }

    @TestCase(key = "ADM08-T1119", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 10)
    public void validarCadastroAditivoContratoEntePublicoComSucessoTest() {
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
        idAditivo = contratoEntePublicoCentralizado.postCadastroAditivoContratoEntePublico(tokenValido,
                        aditivoEntePublicoCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getInt("id");
    }


    @TestCase(key = "ADM08-T1120",keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso",priority = 11)
    public void validarEdicaoAditivoDoContratoEntePublicoComSucessoTest(){
            edicaoAditivoEntePublicoDescentralizadoDTO= contratoEntePublicoCentralizadoDataFactory.edicaoAditivoEntePublicoDescentralizado(
                    12,
                    idContrato,
                    "2023-02-05",
                    new GeradorDatas().dataFimContrato(),
                    1,
                    "stefany_eduarda@sicredi.com.br");
            contratoEntePublicoCentralizado.putEdicaoAditivoContratoEntePublico(tokenValido,edicaoAditivoEntePublicoDescentralizadoDTO,idAditivo)
                    .statusCode(HttpStatus.SC_OK);



    }

    @TestCase(key = "ADM08-T1118", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 12)
    public void validarUploadContratoAditivoAssinadoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadDocumentoAditivoDoContratoAssinado(tokenValido, idAditivo, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1121", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 13)
    public void validarUploadExtratoAditivoContratoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadExtratoAditivoDoContratoAssinado(tokenValido, idAditivo, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1122", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 14)
    public void validarUploadPlanoDeTrabalhoAditivoContratoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadPlanoTrabalhoAditivoDoContratoAssinado(tokenValido, idAditivo, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);


    }
    @TestCase(key = "ADM08-T1123", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 15, description = "")
    public void validarCadastroDistratoDoContratoEntePublicoComSucessoTest() {
        distratoEntePublicoRequestDTO = contratoEntePublicoCentralizadoDataFactory.
                cadastroDistratoEntePublico(idContrato, new GeradorDatas().dataInicioContrato());
        idDistrato = contratoEntePublicoCentralizado.postCadastroDistratoContratoEntePublico(tokenValido,
                        distratoEntePublicoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .jsonPath()
                .getInt("id");

    }

    @TestCase(key = "ADM08-T1124",keyCycle ="ADM08-C1890")
    @Test(groups = "funcional-ente-publico-descentralizado-Com-Sucesso", priority = 16, description = "")
    public void validarEdicaoDistratoDoContratoEntePublicoComSucessoTest(){
        distratoEntePublicoRequestDTO = contratoEntePublicoCentralizadoDataFactory.
                cadastroDistratoEntePublico(idContrato, new GeradorDatas().dataInicioContrato());
        contratoEntePublicoCentralizado.putEdicaoDistratoContratoEntePublico(tokenValido,distratoEntePublicoRequestDTO,idDistrato)
                .statusCode(HttpStatus.SC_OK);
    }




    @TestCase(key = "ADM08-T1118", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 17)
    public void validarUploadContratoDistratoAssinadoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadDocumentoDistratoDoContratoAssinado(tokenValido, idDistrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1125", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 18)
    public void validarUploadExtratoDistratoContratoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadExtratoDistratoDoContratoAssinado(tokenValido, idDistrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1117", keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso", priority = 19)
    public void validarUploadPlanoDeTrabalhoDistratoContratoEntePublicoComSucessoTest() {
        contratoEntePublicoCentralizado.uploadPlanoTrabalhoDistratoDoContratoAssinado(tokenValido, idDistrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1111",keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso",priority = 20)
    public void validarConsultarAditivoDistratoContratoEntePublico(){
            contratoEntePublicoCentralizado.getConsultaAditivoDistratoDoContrato(tokenValido,idContrato)
                    .statusCode(HttpStatus.SC_OK)
                    .body("adendos.id",hasItem(idContrato))
                    .body("adendos.versaoContrato",hasItem(1))
                    .body("adendos.id",hasItem(idAditivo))
                    .body("adendos.versaoContrato",hasItem(2))
                    .body("adendos.id",hasItem(idDistrato))
                    .body("adendos.versaoContrato",hasItem(3));



    }


    @TestCase(key = "ADM08-T1127",keyCycle = "ADM08-C1890")
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso",priority = 21)
    public void validarConsultaContratoCentralizadoComSucessoTest(){
        contratoEntePublicoCentralizado.getConsultaContratoEntePublicoCentralizado(tokenValido,idContrato,1)
                .statusCode(HttpStatus.SC_OK);

    }




    @TestCase(key ="ADM08-T1107",keyCycle ="ADM08-C1890" )
    @Test(groups = "funcional-ente-publico-centralizado-Com-Sucesso",priority = 22)
    public void validarTrocaStatusContratoComSucessoTest(){
        contratoEntePublicoCentralizado.deleteDocumentosDistratoContratoEntePublico(tokenValido,idDistrato)
                        .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoCentralizado.deleteDocumentosAditivoContratoEntePublico(tokenValido,idAditivo)
                        .statusCode(HttpStatus.SC_OK);
            contratoEntePublicoCentralizado.deleteDocumentosContratoEntePublico(tokenValido,idContrato)
                            .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoCentralizado.putDesativaContrato(tokenValido,idDistrato,3)
                        .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoCentralizado.putDesativaContrato(tokenValido,idAditivo,2)
                .statusCode(HttpStatus.SC_OK);
        contratoEntePublicoCentralizado.putDesativaContrato(tokenValido, idContrato, 1)
                .statusCode(HttpStatus.SC_OK);
    }






}
