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

@Listeners({ExtentITestListenerClassAdapter.class})
public class FuncionalPacienteComErroTest extends BaseDataFactory {
    String tokenValido;
    Integer idMedico;
    AgendaRequestDTO agendaRequestDTO;
    LeitosRequestDTO leitosRequestDTO;
    PacienteRequestDTO pacienteRequestDTO;
    BffBaseTest bffBaseTest = new BffBaseTest();
    Integer idAgendaMedico;
    Integer idAgendaExame;


    @BeforeTest(groups = "funcional-admin-Com-Sucesso", description = "Cria token para admin")
    public void geraToken() {
        tokenValido = bffBaseTest.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");

    }
}
