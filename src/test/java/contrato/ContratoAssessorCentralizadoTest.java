package contrato;

import basetest.AssessorPedagogicoBaseTest;
import basetest.BaseDataFactory;
import basetest.ContratoAssessorCentralizadoBaseTest;
import basetest.EmpresaBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.AnaliseOperacionalResquestDTO;
import dto.AssessorCentralizadoRequestDTO;
import dto.AssessorPedagogicoRequestDTO;
import dto.EmpresaRequestDTO;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorNomeDataRg;
import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.is;
@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class ContratoAssessorCentralizadoTest extends BaseDataFactory {

    String tokenValido;
    AssessorPedagogicoBaseTest assessorPedagogico= new AssessorPedagogicoBaseTest();
    EmpresaRequestDTO empresaRequestDTO=new EmpresaRequestDTO();
    EmpresaBaseTest empresaBaseTest=new EmpresaBaseTest();
    Integer idEmpresa;
    AssessorCentralizadoRequestDTO assessorCentralizadoRequestDTO= new AssessorCentralizadoRequestDTO();
    ContratoAssessorCentralizadoBaseTest contratoAssessorCentralizadoBaseTest = new ContratoAssessorCentralizadoBaseTest();
    AssessorPedagogicoRequestDTO assessorPedagogicoRequestDTO = new AssessorPedagogicoRequestDTO();
    AnaliseOperacionalResquestDTO analiseOperacionalResquestDTO = new  AnaliseOperacionalResquestDTO();
    Integer idContrato;




    @BeforeTest(groups = "contrato-assessor-centralizado", description = "Cria base da escola")
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
    @TestCase(key = "ADM08-T1269",keyCycle = "ADM08-C1888")
    @Test(groups = "contrato-assessor-centralizado",priority = 1,description = "Contrato Vigente")
    public void validarContratoCadastroContratoVigenteComSucessoTest(){
            assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
                    .cadastroContratoAssessorCentralizado("0101",
                            new String[]{assessorPedagogicoRequestDTO.getCpf()},
                            null,
                            "31122024",
                            "21112023",
                            "stefany_eduarda@terceiros.sicredi.com.br",
                            null,
                            null,
                            null,
                            idEmpresa,
                            empresaRequestDTO.getCnpjEmpresa()
                            ,1,
                            12,
                            10.50,
                            20.50,
                            30.50,
                            55.50,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null);
            idContrato= contratoAssessorCentralizadoBaseTest.
                    postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response().jsonPath().getInt("id");

            analiseOperacionalResquestDTO=contratoAssessorCentralizadoDataFactory.
                    analiseOperacionalAssessorCentralizado(idContrato,1,"tudo certo","");
            contratoAssessorCentralizadoBaseTest.postAnaliseOperacionalContratoAssessorCentralizado(tokenValido,analiseOperacionalResquestDTO)
                    .statusCode(HttpStatus.SC_CREATED);
            contratoAssessorCentralizadoBaseTest.postContratoAssinado(tokenValido,idContrato, new File("src/main/java/documents/teste.pdf"))
                    .statusCode(HttpStatus.SC_OK);
            contratoAssessorCentralizadoBaseTest.getBuscaContratoGenerico(tokenValido,idContrato,1)
                    .statusCode(HttpStatus.SC_OK)
                    .body(matchesJsonSchema(new File("src/test/resources/json_scherma/getContratoVigente.json")));

    }


}
