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
import utils.GeradorNomeDataRg;
import static org.hamcrest.Matchers.is;

@Listeners({ExtentITestListenerClassAdapter.class})
public class FuncionalAdminComSucessoTest extends BaseDataFactory {
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

    @Test(groups = "funcional-admin-Com-Sucesso", priority = 1, description = "CTR17 Validar POST cadastro de agenda dos médicos e exames ")
    public void validarCadastroAgendaMedicoComSucessoTest() {
        agendaRequestDTO = bffDataFactory.cadastroAgenda(new GeradorDatas().dataInicio(),
                new GeradorDatas().dataFim(),
                "ABRIl",
                "Ricardo d'avila",
                "Mensal",
                null);

        idAgendaMedico = bffBaseTest.postCadastroAgenda(tokenValido, agendaRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("dataInicio", is(agendaRequestDTO.getDataInicio()))
                .body("medicoResponsavel", is(agendaRequestDTO.getMedicoResponsavel()))
                .extract().response().jsonPath().getInt("idAgendaMedico");

    }

    @Test(groups = "funcional-admin-Com-Sucesso", priority = 2, description = "")
    public void validarCadastroAgendaExameComSucessoTest() {
        agendaRequestDTO = bffDataFactory.cadastroAgenda(new GeradorDatas().dataInicio(),
                new GeradorDatas().dataFim(),
                "ABRIl",
                "Ricardo Siqueira",
                "Mensal",
                "Ressonancia");
        idAgendaExame = bffBaseTest.postCadastroAgendaExame(tokenValido, agendaRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .statusCode(HttpStatus.SC_OK)
                .body("nomeMedico", is("Ricardo Siqueira"))
                .body("tipoExame", is("Ressonancia"))
                .extract().response().jsonPath().getInt("idAgendaExame");

    }

    @Test(groups = "funcional-admin-Com-Sucesso",priority = 3,description = "Valida cadastro de leitos")
    public void validarCadastroLeitosComSucessoTest(){
        leitosRequestDTO =bffDataFactory.cadastroLeitos(
                10,
                "Setor 4",
                4,
                "Quarto");

         bffBaseTest.postCadastroLeito(tokenValido,leitosRequestDTO)
                 .statusCode(HttpStatus.SC_OK);
    }

    @Test(groups = "funcional-admin-Com-Sucesso",priority = 4,description = "Valida cadastro de paciente no pronto atendimento")
    public void validarCadastroPacienteProntoAtendimentoComSucessoTest(){
        pacienteRequestDTO= bffDataFactory.cadastroPaciente(
                new GeradorNomeDataRg().geraCPF(),
                "Paciente Teste Automacao "+new GeradorNomeDataRg().geradorNomes(),
                "51982223134",
                "Rua liberdade ",
                "Unimed",
                "Santa Rosa",
                "Celiaco, Leite, Niquel",
                   "120" ,
                "paciente20@gmail.com",
                "Porto Alegre",
                "RS",
                "GastroIntestinal"

        );

        bffBaseTest.postCadastroPacienteAtendimento(tokenValido,pacienteRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("nomePaciente",is(pacienteRequestDTO.getNomePaciente()));
    }

    @Test(groups = "funcional-admin-Com-Sucesso",priority = 5,description = "Valida consulta de agenda marcada por medico")
    public void validarConsultaAgendaMedicoMarcadaComSucessoTest(){
        bffBaseTest.getConsultasAgendadasPorMedico(tokenValido,idMedico)
                .statusCode(HttpStatus.SC_OK)
                .body("nomeMedico",is(agendaRequestDTO.getMedicoResponsavel()))
                .body("quantidadeDiasMarcados",is(10));

    }

    @Test(groups = "funcional-admin-Com-Sucesso",priority = 6,description = "Valida consulta de agenda que o pacinete marcou")
    public void validarConsultaAgendadaPorPacienteComSucesso(){
        bffBaseTest.getConsultasAgendadasPorPaciente(tokenValido,pacienteRequestDTO.getCpfPaciente())
                .statusCode(HttpStatus.SC_OK)
                .body("cpfPaciente",is(pacienteRequestDTO.getNomePaciente()))
                .body("horarioMarcado",is("10:20"))
                .body("medico",is("Ricardo D'avila"));

    }





}



