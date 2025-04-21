package funcional;

import basetest.BaseDataFactory;
import basetest.BffBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import dto.AgendaRequestDTO;
import dto.LeitosRequestDTO;
import dto.PacienteRequestDTO;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;

import static org.hamcrest.Matchers.is;

@Listeners({ExtentITestListenerClassAdapter.class})
public class FuncionalAdminComSucessoTest extends BaseDataFactory {
    String tokenValido;
    Integer idMedico;
    AgendaRequestDTO agendaRequestDTO;
    LeitosRequestDTO leitosRequestDTO;
    PacienteRequestDTO pacienteRequestDTO;
    BffBaseTest bffBaseTest = new BffBaseTest();

    @BeforeTest(groups = "funcional-admin-Com-Sucesso", description = "Cria base da empreesa")
    public void geraToken_CadastraEmpresa() {
        tokenValido = bffBaseTest.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");

    }


    @Test(groups = "funcional-admin-Com-Sucesso", priority = 1, description = "CTR17 Validar POST cadastro de agenda dos médicos e exames ")
    public void validarCadastroAgendaMedicoTest() {
        agendaRequestDTO = bffDataFactory.cadastroAgenda(new GeradorDatas().dataInicio(),
                new GeradorDatas().dataFim(),
                "ABRIl",
                "Ricardo d'avila",
                "Mensal",
                null);

        bffBaseTest.postCadastroAgenda(tokenValido,agendaRequestDTO)
                .body("dataInicio",is(agendaRequestDTO.getDataInicio()))
                .body("medicoResponsavel",is(agendaRequestDTO.getMedicoResponsavel()));

    }

    @Test
    public void validarCadastroAgendaExameTest() {


    }


}



