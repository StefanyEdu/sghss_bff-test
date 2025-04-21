package funcional;

import basetest.AssessorPedagogicoBaseTest;
import basetest.BaseDataFactory;
import basetest.ContratoAssessorCentralizadoBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorNomeDataRg;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalAssessorCentralizadoComErroTest extends BaseDataFactory {

    String tokenValido;
    AssessorPedagogicoBaseTest assessorPedagogico= new AssessorPedagogicoBaseTest();
    Integer idEmpresa;
    AssessorCentralizadoRequestDTO assessorCentralizadoRequestDTO= new AssessorCentralizadoRequestDTO();
    ContratoAssessorCentralizadoBaseTest contratoAssessorCentralizadoBaseTest = new ContratoAssessorCentralizadoBaseTest();
    AnaliseOperacionalResquestDTO analiseOperacionalResquestDTO = new  AnaliseOperacionalResquestDTO();
    Integer idContrato;
    GeradorNomeDataRg corretor= new GeradorNomeDataRg();

    @BeforeTest(groups = "funcional-assessor-centralizado-com-erro", description = "Cria base da empresa")
    public void geraToken_CadastraEmpresa() {
        tokenValido = assessorPedagogico.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");
    }

    @TestCase(key = "ADM08-T1270",keyCycle ="ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-erro",priority = 1,description = "Deve retornar o contrato nao encontrado")
    public void validarContratoNaoEncontradoComErroTest(){
        assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
                .cadastroContratoAssessorCentralizado("0101",
                        new String[]{"23047726175"},
                        null,
                        "31122024",
                        "21112023",
                        "stefany_eduarda@terceiros.sicredi.com.br",
                        null,
                        null,
                        0,
                        idEmpresa,
                        "82527067000143"
                        ,1,
                        12,
                        10.50,
                        20.50,
                        30.50,
                        55.50,
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        null,
                        null);
        String mensagemErro= contratoAssessorCentralizadoBaseTest.postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
                        .extract().response().jsonPath().getString("message");
        assertThat(corretor.respostaApiSemAcento(mensagemErro),
                is( corretor.respostaEsperadaApiSemAcento("Contrato nao encontrado.")));

   }

    @TestCase(key = "ADM08-T1267",keyCycle ="ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-erro",priority = 2,description = "Cadastro de analise operacional invalido")
    public void validarContratoEmAnaliseOperacionalComSucessoTest(){
        analiseOperacionalResquestDTO=contratoAssessorCentralizadoDataFactory.
                analiseOperacionalAssessorCentralizado(idContrato,1,"tudo certo","");
        contratoAssessorCentralizadoBaseTest.postAnaliseOperacionalContratoAssessorCentralizado(tokenValido,analiseOperacionalResquestDTO)
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }
    @TestCase(key = "ADM08-T1268",keyCycle ="ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-erro",priority =3,description = "Assessor que ja possui contrato cadastrado")
    public void validarAssessorQueJaPossuiContratoComErroTest() {
    assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
            .cadastroContratoAssessorCentralizado("0101",
            new String[]{"60478460503"},
            null,
            "31122025",
            "21112023",
            "stefany_eduarda@terceiros.sicredi.com.br",
            null,
            null,
    null,
            idEmpresa,
            "57174022000195"
            ,1,
            12,
            20.50,
            30.50,
            30.50,
            55.55,
            null,
            null,
            null,
            null,
            null,
            null,
            null);
     contratoAssessorCentralizadoBaseTest.
                postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
            .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    }
