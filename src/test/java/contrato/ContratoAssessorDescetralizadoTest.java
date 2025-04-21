package contrato;

import basetest.AssessorPedagogicoBaseTest;
import basetest.BaseDataFactory;
import basetest.ContratoAssessoDescentralizadoBaseTest;
import basetest.EmpresaBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;
import utils.GeradorNomeDataRg;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.is;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class ContratoAssessorDescetralizadoTest extends BaseDataFactory {
    String tokenValido;
    AssessorPedagogicoBaseTest assessorPedagogico = new AssessorPedagogicoBaseTest();
    EmpresaRequestDTO empresaRequestDTO = new EmpresaRequestDTO();
    EmpresaBaseTest empresaBaseTest = new EmpresaBaseTest();
    Integer idEmpresa;
    AssessorPedagogicoRequestDTO assessorPedagogicoRequestDTO = new AssessorPedagogicoRequestDTO();
    AssessorDescentralizadoRequestDTO assessorDescentralizadoRequestDTO = new AssessorDescentralizadoRequestDTO();
    ContratoAssessoDescentralizadoBaseTest contratoAssessoDescentralizadoBaseTest = new ContratoAssessoDescentralizadoBaseTest();
    Integer idContrato;
    BuscaContratoVigenteRequestDTO buscaContratoVigenteRequestDTO= new BuscaContratoVigenteRequestDTO();
    @BeforeTest(groups = "contrato-assessor-centralizado", description = "Cria base da empreesa")
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
                "Criacao", 1, "pedagogia", "20010606", new Integer[]{2});

        assessorPedagogico.putCadastraAssessor(tokenValido, assessorPedagogicoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("idAssessor", is(assessorPedagogicoRequestDTO.getCpf()));
    }

    @TestCase(key = "ADM08-T1266", keyCycle = "ADM08-C1892")
    @Test(groups = "contrato-assessor-descentralizado", priority = 1, description = "")
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
    Integer idContrato=    contratoAssessoDescentralizadoBaseTest.postCadastroContratoAssessorDescentralizado(tokenValido, assessorDescentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
            .extract().response().jsonPath().getInt("id");

        contratoAssessoDescentralizadoBaseTest.postUploadContratoAssinadoAssessor(tokenValido, idContrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);
        buscaContratoVigenteRequestDTO= contratoAssessorCentralizadoDataFactory.
                buscaContratoVigenteAssessorCentralizado("0101",assessorPedagogicoRequestDTO.getCpf(),new GeradorDatas().dataContratoVigente());
        contratoAssessoDescentralizadoBaseTest.postConsultaContratoVigente(tokenValido,buscaContratoVigenteRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchema(new File("src/test/resources/json_scherma/getContratoVigenteDescentralizado.json")));
    }


}
