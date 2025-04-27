package funcional;

import basetest.BaseDataFactory;
import basetest.BffBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.*;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;
import utils.GeradorNomeDataRg;

import static org.hamcrest.Matchers.is;

@Listeners({ExtentITestListenerClassAdapter.class})
public class FuncionalProfissionalSaudeComSucessoTest extends BaseDataFactory {
    String tokenValido;
    Integer idMedico;
    Integer idEnfermeira;
    BffBaseTest bffBaseTest = new BffBaseTest();
    ProfissionalSaudeRequestDTO profissionalSaudeRequestDTO;
    ProntuarioRequestDTO prontuarioRequestDTO;
    ReceitaRequestDTO receitaRequestDTO;

    @BeforeTest(groups = "funcional-profissional-saude-Com-Sucesso", description = "Cria token para admin")
    public void geraToken() {
        tokenValido = bffBaseTest.token("password", "marlene_lopos", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");

    }


    @Test(groups = "funcional-profissional-saude-Com-Sucesso", priority = 1, description = "Validar cadastro de medico")
    public void validarCadastroProfissionalSaudeMedicoComSucessoTest() {
        profissionalSaudeRequestDTO = bffDataFactory.cadastroProfissionalSaude(
                "Profissonal Medico automacao" + new GeradorNomeDataRg().geradorNomes(),
                new GeradorNomeDataRg().geraCPF(),
                "Clinico Geral",
                new GeradorNomeDataRg().codigoCRM(),
                "51982223134",
                "RUa: Dona Alzira",
                "Porto Alegre",
                "Rio Grande do Sul",
                "medico@gmail.com.br"
        );

        idMedico = bffBaseTest.postCadastroProfissionalMedico(tokenValido, profissionalSaudeRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("nomeProfissional", is(profissionalSaudeRequestDTO.getNomeProfissional()))
                .body("cpfCnpjProfissionalSaude", is(profissionalSaudeRequestDTO.getCpfCnpjProfissionalSaude()))
                .extract().response().jsonPath().getInt("idMedico");

    }

    @Test(groups = "funcional-profissional-saude-Com-Sucesso", priority = 2, description = "Validar cadastro de enfermeira")
    public void validarCadastroProfissionalSaudeEnfermeiraComSucessoTest() {
        profissionalSaudeRequestDTO = bffDataFactory.cadastroProfissionalSaude(
                "Profissonal Enfermeira automacao" + new GeradorNomeDataRg().geradorNomes(),
                new GeradorNomeDataRg().geraCPF(),
                "Enfermeira",
                new GeradorNomeDataRg().codigoCRM(),
                "51982223134",
                "RUa: Alzira, 202",
                "Porto Alegre",
                "Rio Grande do Sul",
                "enfermeira@gmail.com.br"
        );

        idEnfermeira = bffBaseTest.postCadastroProfissionalEnfermeira(tokenValido, profissionalSaudeRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("nomeProfissional", is(profissionalSaudeRequestDTO.getNomeProfissional()))
                .body("cpfCnpjProfissionalSaude", is(profissionalSaudeRequestDTO.getCpfCnpjProfissionalSaude()))
                .extract().response().jsonPath().getInt("idEnfermeira");

    }

    @Test(groups = "funcional-profissional-saude-Com-Sucesso", priority = 3, description = "Cadastro de receita para o paciente")
    public void validarCadastroReceitaPacienteComSucessoTest(){
        receitaRequestDTO= bffDataFactory.cadastroReceita(
                "Tomar parecetamol 2x ao dia por 7 dias",
                new GeradorDatas().dataInicio(),
                "RicardoD'Avila");
        bffBaseTest.postCadastroReceita(tokenValido,receitaRequestDTO)
                .statusCode(HttpStatus.SC_OK);

    }

    @Test(groups = "funcional-profissional-saude-Com-Sucesso", priority = 4, description = "Consulta de profissionais de saude cadastrados")
    public void validarConsultaProfissionalSaudeComSucessoTest(){
        bffBaseTest.getConsultasProfissionalSaude(tokenValido,profissionalSaudeRequestDTO.getCpfCnpjProfissionalSaude())
                .statusCode(HttpStatus.SC_OK)
                .body("nomeProfissiona",is(profissionalSaudeRequestDTO.getNomeProfissional()))
                .body("especialidade",is("Enfermeira"));


    }






}
