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

import static org.hamcrest.Matchers.is;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalContratoAssessorDescentralizadoComSucessoTest extends BaseDataFactory {
    String tokenValido;
    AssessorPedagogicoBaseTest assessorPedagogico = new AssessorPedagogicoBaseTest();
    EmpresaRequestDTO empresaRequestDTO = new EmpresaRequestDTO();
    EmpresaBaseTest empresaBaseTest = new EmpresaBaseTest();
    Integer idEmpresa;
    AssessorPedagogicoRequestDTO assessorPedagogicoRequestDTO = new AssessorPedagogicoRequestDTO();
    AssessorDescentralizadoRequestDTO assessorDescentralizadoRequestDTO = new AssessorDescentralizadoRequestDTO();
    ContratoAssessoDescentralizadoBaseTest contratoAssessoDescentralizadoBaseTest = new ContratoAssessoDescentralizadoBaseTest();
    Integer idContrato;
    Integer idAditivo;
    Integer idDistrato;
    BuscaContratoVigenteRequestDTO buscaContratoVigenteRequestDTO = new BuscaContratoVigenteRequestDTO();
    AditivoAssessorDescentralizadoRequestDTO aditivoAssessorDescentralizadoRequestDTO= new AditivoAssessorDescentralizadoRequestDTO();
    DistratoAssessorDescentralizadoRequestDTO distratoAssessorDescentralizadoRequestDTO = new DistratoAssessorDescentralizadoRequestDTO();
    EdicaoAditivoContratoDescentralizadoResquestDTO edicaoAditivoContratoDescentralizadoResquestDTO =  new EdicaoAditivoContratoDescentralizadoResquestDTO();

    @BeforeTest(groups = "funcional-assessor-descentralizado-com-sucesso", description = "Cria base da empreesa")
    public void geraToken_CadastraEmpresa() {
        tokenValido = assessorPedagogico.token("password", "stefany_eduarda", "teste123", "openid")
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
                "stefany_eduarda@terceiros.sicredi.com.br", "51982223134", idEmpresa, new String[]{"0101"}, new String[]{""}, "stefany_eduarda", 1,
                "Criacao", 1, "pedagogia", "06062001", new Integer[]{2});

        assessorPedagogico.putCadastraAssessor(tokenValido, assessorPedagogicoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("idAssessor", is(assessorPedagogicoRequestDTO.getCpf()));
    }

    @TestCase(key = "ADM08-T1180", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 1, description = "")
    public void validarCadastroContratoAssessorDescentralizadoComSucessoTest() {
        assessorDescentralizadoRequestDTO = contratoAssessorDescentralizadoDataFactory.cadastroContratoAssessorDescentralizado(
                true,
                null,
                "0101",
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
                new GeradorDatas().dataInicioNaoIniciado(),
                new GeradorDatas().dataFimContrato(),
                12,
                0,
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
    }

    @TestCase(key = "ADM08-T1181", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 2, description = "")
    public void validarEdicaoContratoAssessorDescentralizadoComSucessoTest() {
        assessorDescentralizadoRequestDTO = contratoAssessorDescentralizadoDataFactory.cadastroContratoAssessorDescentralizado(
                true,
                idContrato,
                "0101",
                "stefany_eduarda@sicredi.com.br",
                new String[]{assessorPedagogicoRequestDTO.getCpf()},
                idEmpresa,
                empresaRequestDTO.getCnpjEmpresa(),
                "",
                10.20,
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

        contratoAssessoDescentralizadoBaseTest.putEdicaoContratoAssessor(tokenValido, assessorDescentralizadoRequestDTO, idContrato)
                .statusCode(HttpStatus.SC_OK);


    }

    @TestCase(key = "ADM08-T1187", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 3, description = "")
    public void validarUploadContratoAssinadoAssessorDescentralizadoComSucessoTest() {
        contratoAssessoDescentralizadoBaseTest.postUploadContratoAssinadoAssessor(tokenValido, idContrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

    }
    @TestCase(key = "ADM08-T1246")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso",priority = 4,description = "")
    public void validarExclusaoDocumentoAssinadoContratoComSucessoTest(){
        contratoAssessoDescentralizadoBaseTest.deleteDocumentoAssinadoContrato(tokenValido,idContrato)
                .statusCode(HttpStatus.SC_OK);
        contratoAssessoDescentralizadoBaseTest.postUploadContratoAssinadoAssessor(tokenValido, idContrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1182", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 5, description = "")
    public void validarCadastroAditivoContratoAssessorDescentralizadoComSucessoTest() {
    aditivoAssessorDescentralizadoRequestDTO= contratoAssessorDescentralizadoDataFactory.cadastroAditivoContratoAssessorDescentralizado(
        true,
        idContrato,
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
        null,null,null);

        idAditivo = contratoAssessoDescentralizadoBaseTest.postCadastroAditivoContratoAssessor(tokenValido, aditivoAssessorDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getInt("id");

    }

    @TestCase(key = "ADM08-T1183", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 6, description = "")
    public void validarEdicaoAditivoContratoAssessorDescentralizadoComSucessoTest() {
        edicaoAditivoContratoDescentralizadoResquestDTO= contratoAssessorDescentralizadoDataFactory.edicaoAditivoContratoDescentralizado(
                true,
                idContrato,
                "",
                20.3,
                30.2,
                39.50,
                80.00,
                "",
                new GeradorDatas().dataInicioContrato(),
                new GeradorDatas().dataFimContrato(),
                13,
                0,
                null,
                null,
                null,
                null,
                null,null,null);
        contratoAssessoDescentralizadoBaseTest.putEdicaoAditivoContratoAssessor(tokenValido, edicaoAditivoContratoDescentralizadoResquestDTO,idAditivo)
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1188", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 7, description = "")
    public void validarUploadContratoAssinadoAditivoComSucessoTest() {
        contratoAssessoDescentralizadoBaseTest.postUploadContratoAditivoAssessor(tokenValido, idAditivo, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1278")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso",priority = 8,description = "")
    public void validarExclusaoDocumentoAssinadoAditivoDoContratoComSucessoTest(){
        contratoAssessoDescentralizadoBaseTest.deleteDocumentoAssinadoAditivoDoContrato(tokenValido,idAditivo)
                .statusCode(HttpStatus.SC_OK);
        contratoAssessoDescentralizadoBaseTest.postUploadContratoAditivoAssessor(tokenValido, idAditivo, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }
    @TestCase(key = "ADM08-T1186",keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso",priority = 9,description = "")
    public void validarConsultaContratoVigenteComSucessoTest(){
        buscaContratoVigenteRequestDTO= contratoAssessorCentralizadoDataFactory.
                buscaContratoVigenteAssessorCentralizado("0101",assessorPedagogicoRequestDTO.getCpf(),new GeradorDatas().dataContratoVigente());
        contratoAssessoDescentralizadoBaseTest.postConsultaContratoVigente(tokenValido,buscaContratoVigenteRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("codCoop",is("0101"))
                .body("idEmpresa",is(idEmpresa))
                .body("cnpjEmpresa",is(empresaRequestDTO.getCnpjEmpresa()));
    }
    @TestCase(key = "ADM08-T1184", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 10, description = "")
    public void validarCadastroDistratoContratoAssessorDescentralizadoComSucessoTest() {
    distratoAssessorDescentralizadoRequestDTO= contratoAssessorDescentralizadoDataFactory.cadastroDistratoContratoAssessorDescentralizado(
            null,
            idContrato,
            true,
            "",
            new GeradorDatas().dataInicioContrato(),
            0);

    idDistrato= contratoAssessoDescentralizadoBaseTest.postCadastroDistratoContratoAssessor(tokenValido, distratoAssessorDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
            .extract().response().jsonPath().getInt("id");

    }

    @TestCase(key = "ADM08-T1185", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 11, description = "")
    public void validarEdicaoDistratoContratoAssessorDescentralizadoComSucessoTest(){
        distratoAssessorDescentralizadoRequestDTO= contratoAssessorDescentralizadoDataFactory.cadastroDistratoContratoAssessorDescentralizado(
                idDistrato,
                idContrato,
                true,
                "",
                new GeradorDatas().dataInicioContrato(),
                0);
        contratoAssessoDescentralizadoBaseTest.putEdicaoDistratoContratoAssessor(tokenValido, distratoAssessorDescentralizadoRequestDTO,idDistrato)
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1187", keyCycle = "ADM08-C1892")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso", priority = 12, description = "")
    public void validarUploadContratoAssinadoDistratoComSucessoTest() {
       contratoAssessoDescentralizadoBaseTest.postContratoAssinadoDistrato(tokenValido,idDistrato, new File("src/main/java/documents/teste.pdf"))
               .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1279")
    @Test(groups = "funcional-assessor-descentralizado-com-sucesso",priority = 13,description = "")
    public void validarExclusaoDocumentoAssinadoDistratoDoContratoComSucessoTest(){
        contratoAssessoDescentralizadoBaseTest.deleteDocumentoAssinadoDistratoDoContrato(tokenValido,idDistrato)
                .statusCode(HttpStatus.SC_OK);
        contratoAssessoDescentralizadoBaseTest.postContratoAssinadoDistrato(tokenValido,idDistrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
    }



}



