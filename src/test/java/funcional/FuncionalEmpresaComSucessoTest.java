package funcional;

import basetest.BaseDataFactory;
import basetest.EmpresaBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.AssessorEmpresaRequestDTO;
import dto.AssessorVinculoRequestDTO;
import dto.EmpresaPaginaRequestDTO;
import dto.EmpresaRequestDTO;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorNomeDataRg;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalEmpresaComSucessoTest extends BaseDataFactory {
    String tokenValido;
    EmpresaBaseTest empresaBaseTest = new EmpresaBaseTest();
    EmpresaRequestDTO empresaRequestDTO = new EmpresaRequestDTO();
    EmpresaPaginaRequestDTO empresaPaginaRequestDTO = new EmpresaPaginaRequestDTO();
    Integer idEmpresa;
    AssessorVinculoRequestDTO assessorVinculoRequestDTO = new AssessorVinculoRequestDTO();
    AssessorEmpresaRequestDTO assessorEmpresaRequestDTO = new AssessorEmpresaRequestDTO();

    @BeforeTest(groups = "funcional-empresa-Com-Sucesso")
    public void geraToken() {
        tokenValido = empresaBaseTest.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");
    }

    @TestCase(key = "ADM08-T1190", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 1, description = "Deve cadastrar empresa ")
    public void validarCadastroEmpresaComSucessoTest() {
        empresaRequestDTO = empresaDataFactory.cadastraEmpresa("Rubem Berta", "91250554", "NOVA PETROPOLIS"
                , new GeradorNomeDataRg().geradorCnpj(false), "Rua Paulo Renato", "Predio 1", 23, "Empresa Automação" + new GeradorNomeDataRg().geradorNomes(),
                "440");
        empresaBaseTest.postCadastraEmpresa(tokenValido, empresaRequestDTO).statusCode(HttpStatus.SC_CREATED);
        idEmpresa = empresaBaseTest.getConsultaEmpresaPorCnpj(tokenValido, empresaRequestDTO.getCnpjEmpresa())
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getInt("id");
    }

    @TestCase(key = "ADM08-T1194", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 2, description = "Deve consulta empresa por cnpj")
    public void validarConsultaEmpresaPorCnpjComSucessoTest() {
        empresaBaseTest.getConsultaEmpresaPorCnpj(tokenValido, empresaRequestDTO.getCnpjEmpresa())
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(idEmpresa))
                .body("nome", is(empresaRequestDTO.getNome()))
                .body("cnpjEmpresa", is(empresaRequestDTO.getCnpjEmpresa()));


    }

    @TestCase(key = "ADM08-T1192", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 3, description = "Deve consultar empresa por id")
    public void validarConsultaEmpresaPorIdComSucessoTest() {
        empresaBaseTest.getConsultaEmpresaPorId(tokenValido, idEmpresa)
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(idEmpresa))
                .body("nome", is(empresaRequestDTO.getNome()))
                .body("cnpjEmpresa", is(empresaRequestDTO.getCnpjEmpresa()));

    }

    @TestCase(key = "ADM08-T1191", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 4, description = "Deve editar a empresa alterando seus valores ")
    public void validarEdicaoEmpresaComSucessoTest() {
        empresaRequestDTO = empresaDataFactory.cadastraEmpresa("Rubem Berta", "91250554", "NOVA PETROPOLIS"
                , new GeradorNomeDataRg().geradorCnpj(false), "Predio 1", "Rua Paulo Renato", 23, "Empresa Automacao" + new GeradorNomeDataRg().geradorNomes(),
                "440");
        empresaBaseTest.putAlteraCadastroEmpresa(tokenValido, idEmpresa, empresaRequestDTO)
                .statusCode(HttpStatus.SC_OK);
        empresaBaseTest.getConsultaEmpresaPorId(tokenValido, idEmpresa)
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(idEmpresa))
                .body("nome", is(empresaRequestDTO.getNome()))
                .body("cnpjEmpresa", is(empresaRequestDTO.getCnpjEmpresa()))
                .body("endereco", is(empresaRequestDTO.getEndereco()));

    }

    @TestCase(key = "ADM08-T1195", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 5, description = "Deve vincular o assessor a empresa ")
    public void validarVinculoAssessorComEmpresaComSucessoTest() {
        assessorVinculoRequestDTO = empresaDataFactory.vinculoAssessor(new String[]{new GeradorNomeDataRg().geraCPF()});
        empresaBaseTest.postBuscaEmpresaPorCnpjEAdicionaNovosAssessores(tokenValido, empresaRequestDTO.getCnpjEmpresa(), assessorVinculoRequestDTO)
                .statusCode(HttpStatus.SC_OK);
    }

    @TestCase(key = "ADM08-T1199", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 6, description = "Deve Buscar a empresa do assessor")
    public void validarBuscaEmpresaPorCpfAssessorComSucessoTest() {
        empresaBaseTest.getListaEmpresaPorAssessor(tokenValido, "67918387015")
                .statusCode(HttpStatus.SC_OK)
                .body("nome",is("EMPRESA GAUCHA"));

    }

    @TestCase(key = "ADM08-T1198", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 7, description = "busca empresas por coops")
    public void validarBuscaEmpresaPorCooperativaComSucessoTest() {
        String cnpj= "56199674000111";
        String nomeEmpresa= "EMPRESA GAUCHA";
        empresaBaseTest.getListaEmpresaPorCoop(tokenValido,"0116")
                .statusCode(HttpStatus.SC_OK)
                .body("findAll { it.id == " + 241 + " }.nome", hasItem(nomeEmpresa))
                .body("findAll { it.id == " + 241 + " }.cnpjEmpresa", hasItem(cnpj));
    }



    @TestCase(key = "ADM08-T1197", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 8, description = "Busca todas as empresas")
    public void validarBuscaTodasEmpresasComSucessoTest() {
        empresaBaseTest.getListaTodasEmpresas(tokenValido)
                .body("findAll { it.id == " + idEmpresa + " }.nome", hasItem(empresaRequestDTO.getNome()))
                .body("findAll { it.id == " + idEmpresa + " }.cnpjEmpresa", hasItem(empresaRequestDTO.getCnpjEmpresa()));

    }

    @TestCase(key = "ADM08-T1196", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 9, description = "Busca empresa por cidade e estado conforme tiver vinculo com assessor")
    public void validarBuscasAssessorVinculoEmpresaPorEstadoCidadeComSucessoTest() {
        assessorEmpresaRequestDTO=empresaDataFactory.buscaAssessorPorEstadoCidade(new Integer[]{23},new String[]{"PORTO ALEGRE"});
        empresaBaseTest.postBuscaAssessorPorEstadoCidade(tokenValido,assessorEmpresaRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("cpf",hasItem("09835195013"))
                .body("nome", hasItem("abelão assessor teste campo"));

    }

    @TestCase(key = "ADM08-T1193", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 10, description = "Busca paginada das empresas por cidade e estado")
    public void validarBuscaEmpresasPaginasComSucessoTest() {
        empresaPaginaRequestDTO= empresaDataFactory.buscaPaginada("",new Integer[]{23},new String[]{"NOVA PETROPOLIS"},new String[]{});
        empresaBaseTest.postBuscaEmpresaPagina(tokenValido,empresaPaginaRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("elements.id",hasItem(idEmpresa))
                .body("elements.nome",hasItem(empresaRequestDTO.getNome()));


    }

    @TestCase(key = "ADM08-T1200", keyCycle = "ADM08-C1889")
    @Test(groups = "funcional-empresa-Com-Sucesso", priority = 11, description = "Exclui a empresa")
    public void validarExcluirEmpresaComSucessoTest() {
        empresaBaseTest.deleteEmpresaPorId(tokenValido,idEmpresa);

    }


}
