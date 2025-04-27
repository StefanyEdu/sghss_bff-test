package funcional;

import basetest.BaseDataFactory;
import basetest.BffBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.PacienteAgendaRequestDTO;
import dto.PacienteRequestDTO;
import org.apache.http.HttpStatus;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorNomeDataRg;

import static org.hamcrest.Matchers.is;

@Listeners({ExtentITestListenerClassAdapter.class})
public class FuncionalPacienteComSucessoTest extends BaseDataFactory {
    String tokenValido;
    PacienteRequestDTO pacienteRequestDTO;
    BffBaseTest bffBaseTest = new BffBaseTest();
    PacienteAgendaRequestDTO pacienteAgendaRequestDTO;
    Integer idPaciente;


    @Test(groups = "funcional-paciente-Com-Sucesso", priority = 1, description = "")
    public void validarCadastroPacienteComSucessoTest() {
        pacienteRequestDTO = bffDataFactory.cadastroPaciente(
                new GeradorNomeDataRg().geraCPF(),
                "Paciente automacao" + new GeradorNomeDataRg().geradorNomes(),
                "51982223134",
                "Rua das flores",
                "Unimed",
                "Floresta",
                "Latex,Parecetamol",
                "233",
                "paciente@gmai.com",
                "Porto Alegre",
                "Rio Grande do Sul",
                null
        );
        idPaciente = bffBaseTest.postCadastroPaciente(tokenValido, pacienteRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("idPaciente", is(pacienteRequestDTO.getCpfPaciente()))
                .body("nomePaciente", is(pacienteRequestDTO.getNomePaciente()))
                .extract().response().jsonPath().getInt("idPaciente");

    }

    @Test(groups = "funcional-paciente-Com-Sucesso", priority = 2, description = "")
    public void validarEdicaoPacienteComSucessoTest() {
        pacienteRequestDTO = bffDataFactory.cadastroPaciente(
                new GeradorNomeDataRg().geraCPF(),
                "Paciente automacao" + new GeradorNomeDataRg().geradorNomes(),
                "51982223134",
                "Rua das flores",
                "Unimed",
                "Floresta",
                "Latex,Parecetamol, alergica a leite",
                "233",
                "paciente@gmai.com",
                "Porto Alegre",
                "Rio Grande do Sul",
                null
        );

        bffBaseTest.putCadastroPaciente(tokenValido, pacienteRequestDTO)
                .statusCode(HttpStatus.SC_OK);


    }

    @Test(groups = "funcional-paciente-Com-Sucesso", priority = 1, description = "")
    public void validarCadastroAgendaPorPacienteComSucessoTest() {
        pacienteAgendaRequestDTO = bffDataFactory.cadastroPacienteAgenda(
                pacienteRequestDTO.getNomePaciente(),
                "Ricardo D'Avila",
                "Clinico Geral",
                "14:00",
                "30/01/2025",
                null);

        bffBaseTest.postCadastroAgendaPaciente
                (tokenValido,pacienteRequestDTO.getCpfPaciente()
                        ,pacienteAgendaRequestDTO)
                .statusCode(HttpStatus.SC_OK);

    }




}
