package funcional;

import basetest.AssessorPedagogicoBaseTest;
import basetest.BaseDataFactory;
import basetest.ContratoAssessoDescentralizadoBaseTest;
import basetest.EmpresaBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.*;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;
import utils.GeradorNomeDataRg;


import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalContratoAssessorDescentralizadoComErroTest extends BaseDataFactory {

    String tokenValido;
    AssessorPedagogicoBaseTest assessorPedagogico = new AssessorPedagogicoBaseTest();
    Integer idEmpresa;
    AssessorPedagogicoRequestDTO assessorPedagogicoRequestDTO = new AssessorPedagogicoRequestDTO();
    AssessorDescentralizadoRequestDTO assessorDescentralizadoRequestDTO = new AssessorDescentralizadoRequestDTO();
    ContratoAssessoDescentralizadoBaseTest contratoAssessoDescentralizadoBaseTest = new ContratoAssessoDescentralizadoBaseTest();
    Integer idContrato;
    AditivoAssessorDescentralizadoRequestDTO aditivoAssessorDescentralizadoRequestDTO = new AditivoAssessorDescentralizadoRequestDTO();
    GeradorNomeDataRg corretor = new GeradorNomeDataRg();
    EmpresaRequestDTO empresaRequestDTO = new EmpresaRequestDTO();
    EmpresaBaseTest empresaBaseTest= new EmpresaBaseTest();

    @BeforeTest(groups = "funcional-assessor-descentralizado-com-erro", description = "Cria base da empreesa")
    public void geraToken_CadastraEmpresa() {
        tokenValido = assessorPedagogico.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");
    }

//    @TestCase(key = "ADM08-T1234", keyCycle = "ADM08-C1892")
//    @Test(groups = "funcional-assessor-descentralizado-com-erro", priority = 1, description = "Cadastro invalido sem programas educacionais")
//    public void validarCadastroContratoInvalidoSemProgramasEducacionaisComErroTest() {
//        assessorDescentralizadoRequestDTO = contratoAssessorDescentralizadoDataFactory.cadastroContratoAssessorDescentralizado(
//                true,
//                null,
//                "0101",
//                "stefany_eduarda@sicredi.com.br",
//                new String[]{"71851350012"},
//                idEmpresa,
//                "53158235000172",
//                "",
//                10.10,
//                20.10,
//                30.10,
//                30.20,
//                "",
//                new GeradorDatas().dataInicioContrato(),
//                new GeradorDatas().dataFimContrato(),
//                12,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                true,
//                new Integer[]{null},
//                null);
//        contratoAssessoDescentralizadoBaseTest.postCadastroContratoAssessorDescentralizado(tokenValido, assessorDescentralizadoRequestDTO)
//                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
//                .body("message", is("Programa Educacional informado é inválido."));
//    }

//    @TestCase(key = "ADM08-T1268", keyCycle = "ADM08-C1892")
//    @Test(groups = "funcional-assessor-descentralizado-com-erro", priority = 2, description = "")
//    public void validarEdicaoDistratoContratoAssessorDescentralizadoComSucessoTest() {
//        assessorDescentralizadoRequestDTO = contratoAssessorDescentralizadoDataFactory.cadastroContratoAssessorDescentralizado(
//                true,
//                null,
//                "0101",
//                "stefany_eduarda@sicredi.com.br",
//                new String[]{"71851350012"},
//                idEmpresa,
//                "53158235000172",
//                "",
//                10.10,
//                20.10,
//                30.10,
//                30.20,
//                "",
//                new GeradorDatas().dataInicioContrato(),
//                new GeradorDatas().dataFimContrato(),
//                12,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null,
//                true,
//                new Integer[]{1},
//                null);
//        contratoAssessoDescentralizadoBaseTest.postCadastroContratoAssessorDescentralizado(tokenValido, assessorDescentralizadoRequestDTO)
//                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
//
//
//
//    }

    @TestCase(key = "ADM08-T262", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-erro", priority = 3, description = "Cadastro do aditivo invalido contrato invalido")
    public void validarCadastroAditivoContratoAssessorDescentralizadoComErroTest() {
        aditivoAssessorDescentralizadoRequestDTO = contratoAssessorDescentralizadoDataFactory.cadastroAditivoContratoAssessorDescentralizado(
                true,
                0,
                "",
                10.3,
                30.2,
                39.0,
                49.0,
                "",
                new GeradorDatas().dataInicioContrato(),
                new GeradorDatas().dataFimContrato(),
                12,
                0,
                null,
                null,
                null,
                null,
                null, null, null);

        String mensagemErro =contratoAssessoDescentralizadoBaseTest.postCadastroAditivoContratoAssessor(tokenValido, aditivoAssessorDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
                .extract().response().jsonPath().getString("message");
                assertThat(corretor.respostaApiSemAcento(mensagemErro),
                        is(corretor.respostaEsperadaApiSemAcento("Contrato nao encontrado.")));

    }

    @TestCase(key = "ADM08-T1270", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-erro", priority = 4, description = "Sem campos obrigatorio")
    public void validarAditivoSemCamposObrigatorioComErroTest() {
        aditivoAssessorDescentralizadoRequestDTO = contratoAssessorDescentralizadoDataFactory.cadastroAditivoContratoAssessorDescentralizado(
                true,
                idContrato,
                "",
                null,
                null,
                null,
                null,
                "",
                new GeradorDatas().dataInicioContrato(),
                new GeradorDatas().dataFimContrato(),
                12,
                0,
                null,
                null,
                null,
                null,
                null, null, null);

        contratoAssessoDescentralizadoBaseTest.postCadastroAditivoContratoAssessor(tokenValido, aditivoAssessorDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }
//
//    @TestCase(key = "ADM08-T1270", keyCycle = "ADM08-C1892")
//    @Test(groups = "funcional-assessor-descentralizado-com-erro", priority = 5, description = "")
//    public void validarCadastroContratoAssessorDescentralizadoComErroTest() {
//        aditivoAssessorDescentralizadoRequestDTO = contratoAssessorDescentralizadoDataFactory.cadastroAditivoContratoAssessorDescentralizado(
//                true,
//                //4205
//                4887,
//                "",
//                10.3,
//                30.2,
//                39.0,
//                49.0,
//                "",
//                new GeradorDatas().dataInicioContrato(),
//                new GeradorDatas().dataFimContrato(),
//                12,
//                0,
//                null,
//                null,
//                null,
//                null,
//                null, null, null);
//
//        String mensagemErro = contratoAssessoDescentralizadoBaseTest.postCadastroAditivoContratoAssessor(tokenValido, aditivoAssessorDescentralizadoRequestDTO)
//                .statusCode(HttpStatus.SC_EXPECTATION_FAILED)
//                .extract().response().jsonPath().getString("message");
//        assertThat(corretor.respostaApiSemAcento(mensagemErro),
//                is(corretor.respostaEsperadaApiSemAcento("Nao e possível criar aditivo para um contrato ainda nao assinado.")));
//
//
//    }

    @TestCase(key = "ADM08-T1277", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-erro", priority = 6, description = "")
    public void validarExclusaoDocumentoAssinadoContrato() {
        tokenValido = assessorPedagogico.token("password", "igor_morais", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");
       empresaRequestDTO = empresaDataFactory.cadastraEmpresa("Rubem Berta", "91250554", "Porto Alegre"
                , new GeradorNomeDataRg().geradorCnpj(false), "Rua Paulo Renato", "Predio 1", 23, "Empresa Automação" + new GeradorNomeDataRg().geradorNomes(),
                "440");
        empresaBaseTest.postCadastraEmpresa(tokenValido, empresaRequestDTO).statusCode(HttpStatus.SC_CREATED);
        idEmpresa = empresaBaseTest.getConsultaEmpresaPorCnpj(tokenValido, empresaRequestDTO.getCnpjEmpresa())
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getInt("id");

        assessorPedagogicoRequestDTO = assessorPedagogicoDataFactory.cadastroAssessor("Assessor Automacao" + new GeradorNomeDataRg().geradorNomes(), new GeradorNomeDataRg().geraCPF(),
                "stefany_eduarda@terceiros.sicredi.com.br", "51982223134", idEmpresa, new String[]{"0740"}, new String[]{""}, "stefany_eduarda", 1,
                "Criacao", 1, "pedagogia", "06062001", new Integer[]{2});

        assessorPedagogico.putCadastraAssessor(tokenValido, assessorPedagogicoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("idAssessor", is(assessorPedagogicoRequestDTO.getCpf()));
        assessorDescentralizadoRequestDTO = contratoAssessorDescentralizadoDataFactory.cadastroContratoAssessorDescentralizado(
                true,
                null,
                "0740",
                "stefany_eduarda@sicredi.com.br",
                new String[]{assessorPedagogicoRequestDTO.getCpf()},
                idEmpresa,
                empresaRequestDTO.getCnpjEmpresa(),
                "",
                10.10,
                20.10,
                30.10,
                30.20,
                "",
                new GeradorDatas().dataInicioContrato(),
                new GeradorDatas().dataFimContrato(),
                12,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                new Integer[]{1},
                null);
        idContrato = contratoAssessoDescentralizadoBaseTest.postCadastroContratoAssessorDescentralizado(tokenValido, assessorDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getInt("id");

       contratoAssessoDescentralizadoBaseTest.postUploadContratoAssinadoAssessor(tokenValido, idContrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
        contratoAssessoDescentralizadoBaseTest.deleteDocumentoAssinadoContrato(tokenValido, idContrato)
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);


    }
}